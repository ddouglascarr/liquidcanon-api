package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

public class AreaRepositoryImpl implements AreaRepositoryCustom
{
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Area> findByUnitId(Long unitId)
    {
        final String sql = "SELECT * FROM area WHERE unit_id = :unitId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("unitId", unitId);
        List<Area> areas = this.namedParameterJdbcTemplate.query(
                sql,
                namedParameters,
                new BeanPropertyRowMapper(Area.class));
        return areas;
    }

}
