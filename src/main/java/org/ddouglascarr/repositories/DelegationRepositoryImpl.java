package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Delegation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DelegationRepositoryImpl implements DelegationRepository
{
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Delegation findUnitDelegationByTrusterId(Long unitId, Long trusterId)
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
    public Delegation findAreaDelegationByTrusterId(Long unitId, Long areaId, Long trusterId)
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
            Delegation delegation = (Delegation) namedParameterJdbcTemplate.queryForObject(
                    sql,
                    namedParameters,
                    new BeanPropertyRowMapper(Delegation.class));
            return delegation;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Delegation> findAreaDelegationsByUnitIdAndTrusterId(Long unitId, Long trusterId)
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
                new BeanPropertyRowMapper(Delegation.class));
        return delegations;
    }

    @Override
    public Delegation findOneById(Long unitId, Long id)
    {
        return null;
    }

    @Override
    public List<Delegation> findByTrusterId(Long unitId, Long trusterId)
    {
        Delegation unitDelegation = this.findUnitDelegationByTrusterId(unitId, trusterId);
        List<Delegation> delegations = this
                .findAreaDelegationsByUnitIdAndTrusterId(unitId, trusterId);
        if (null != unitDelegation) delegations.add(unitDelegation);
        return delegations;
    }

    @Override
    public List<Delegation> findUnitDelegationsByTrusteeId(Long unitId, Long trusteeId)
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
                new BeanPropertyRowMapper(Delegation.class)
        );
        return delegations;
    }

    @Override
    public List<Delegation> findAreaDelegationsByTrusteeId(Long unitId, Long areaId, Long trusteeId)
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
                new BeanPropertyRowMapper(Delegation.class));
        return delegations;
    }
}
