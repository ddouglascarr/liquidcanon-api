package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Delegation;
import org.springframework.beans.factory.annotation.Autowired;
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
        Delegation delegation = (Delegation) namedParameterJdbcTemplate.queryForObject(
                sql,
                namedParameters,
                new BeanPropertyRowMapper(Delegation.class)
        );
        return delegation;
    }

    @Override
    public Delegation findOneById(Long unitId, Long id)
    {
        return null;
    }

    @Override
    public List<Delegation> findAllForUnitByTrusterId(Long unitId, Long trusterId)
    {
        return null;
    }

    @Override
    public List<Delegation> findAllForUnitByTrusteeId(Long unitId, Long trusteeId)
    {
        return null;
    }
}
