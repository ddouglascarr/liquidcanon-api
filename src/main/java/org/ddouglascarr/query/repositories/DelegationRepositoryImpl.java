package org.ddouglascarr.query.repositories;

import org.ddouglascarr.query.models.Delegation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class DelegationRepositoryImpl implements DelegationRepository
{
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Delegation findUnitDelegationByTrusterId(UUID unitId, UUID trusterId)
    {
        final String sql = String.join(" ",
                "SELECT * FROM delegation",
                "WHERE unit_id = :unitId",
                "AND truster_id = :trusterId");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("unitId", unitId);
        namedParameters.addValue("trusterId", trusterId);
        try {
            Delegation delegation = (Delegation) namedParameterJdbcTemplate.queryForObject(
                    sql,
                    namedParameters,
                    new BeanPropertyRowMapper(Delegation.class)
            );
            return delegation;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Delegation findAreaDelegationByTrusterId(UUID unitId, UUID areaId, UUID trusterId)
    {
        final String sql = String.join(" ",
                "SELECT delegation.* FROM",
                "area_delegation AS ad",
                "JOIN",
                "delegation ON delegation.id = ad.id",
                "WHERE ad.unit_id = :unitId",
                    "AND ad.area_id = :areaId",
                    "AND ad.truster_id = :trusterId",
                    "AND ad.scope = 'area'");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("unitId", unitId);
        namedParameters.addValue("areaId", areaId);
        namedParameters.addValue("trusterId", trusterId);
        try {
            Delegation delegation = namedParameterJdbcTemplate.queryForObject(
                    sql,
                    namedParameters,
                    new BeanPropertyRowMapper<>(Delegation.class));
            return delegation;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Delegation> findAreaDelegationsByUnitIdAndTrusterId(UUID unitId, UUID trusterId)
    {
        String sql = String.join(" ",
                "SELECT delegation.* FROM delegation",
                "JOIN",
                "area_delegation AS ad ON ad.id = delegation.id",
                "WHERE ad.truster_id = :trusterId AND ad.unit_id = :unitId",
                    "AND ad.scope = 'area'");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("unitId", unitId);
        namedParameters.addValue("trusterId", trusterId);
        List<Delegation> delegations = namedParameterJdbcTemplate.query(
                sql,
                namedParameters,
                new BeanPropertyRowMapper<>(Delegation.class));
        return delegations;
    }

    @Override
    public Delegation findOneById(UUID unitId, UUID id)
    {
        return null;
    }

    @Override
    public List<Delegation> findByTrusterId(UUID unitId, UUID trusterId)
    {
        Delegation unitDelegation = this.findUnitDelegationByTrusterId(unitId, trusterId);
        List<Delegation> delegations = this
                .findAreaDelegationsByUnitIdAndTrusterId(unitId, trusterId);
        if (null != unitDelegation) delegations.add(unitDelegation);
        return delegations;
    }

    @Override
    public List<Delegation> findUnitDelegationsByTrusteeId(UUID unitId, UUID trusteeId)
    {
        final String sql = String.join(" ",
                "SELECT * FROM delegation",
                "WHERE trustee_id = :trusteeId",
                "AND unit_id = :unitId");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("unitId", unitId);
        namedParameters.addValue("trusteeId", trusteeId);
        List<Delegation> delegations = namedParameterJdbcTemplate.query(
                sql,
                namedParameters,
                new BeanPropertyRowMapper<>(Delegation.class)
        );
        return delegations;
    }

    @Override
    public List<Delegation> findAreaDelegationsByTrusteeId(UUID unitId, UUID areaId, UUID trusteeId)
    {
        final String sql = String.join(" ",
                "SELECT d.*",
                "FROM (SELECT * FROM delegation",
                "WHERE area_id = :areaId",
                "AND trustee_id = :trusteeId) AS d",
                "JOIN",
                "(SELECT area.* FROM area WHERE id = :areaId AND unit_id = :unitId) AS a",
                "ON area_id = d.area_id");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("unitId", unitId);
        namedParameters.addValue("areaId", areaId);
        namedParameters.addValue("trusteeId", trusteeId);
        List<Delegation> delegations = namedParameterJdbcTemplate.query(
                sql,
                namedParameters,
                new BeanPropertyRowMapper<>(Delegation.class));
        return delegations;
    }
}
