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
    public Delegation findOneUnitDelegationByTrusterId(Long unitId, Long trusterId)
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
    public Delegation findOneById(Long unitId, Long id)
    {
        return null;
    }

    @Override
    public List<Delegation> findByTrusterId(Long unitId, Long trusterId)
    {
        return null;
    }

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
}
