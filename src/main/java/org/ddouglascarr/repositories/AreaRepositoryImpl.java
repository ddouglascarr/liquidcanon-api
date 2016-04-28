package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.util.List;

public class AreaRepositoryImpl implements AreaRepositoryCustom
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Area> findByUnitId(Long unitId)
    {
        String query = "SELECT * FROM area WHERE unit_id = ?";
        List<Area> areas = jdbcTemplate.query(
                query,
                new Object[] {unitId},
                new BeanPropertyRowMapper(Area.class));
        return areas;
    }

}
