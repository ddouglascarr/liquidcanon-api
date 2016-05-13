package org.ddouglascarr.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AreaRepositoryImpl implements AreaRepository
{
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Area findOne(Long id) throws ItemNotFoundException
    {
        final String sql = String.join(" ",
                "SELECT * FROM area WHERE id = :id");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        try {
            Area area = namedParameterJdbcTemplate.queryForObject(
                    sql, mapSqlParameterSource, new BeanPropertyRowMapper<Area>(Area.class));
            return area;
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public Area findOneByUnitIdAndId(Long unitId, Long id) throws ItemNotFoundException
    {
        return null;
    }

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
