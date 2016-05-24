package org.ddouglascarr.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AreaRepositoryImpl implements AreaRepository
{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Area findOne(UUID id) throws ItemNotFoundException
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
    public Area findOneByUnitIdAndId(UUID unitId, UUID id) throws ItemNotFoundException
    {
        final String sql = String.join(" ",
                "SELECT * FROM area",
                "WHERE unit_id = :unitId AND id = :id");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("unitId", unitId);
        mapSqlParameterSource.addValue("id", id);
        try {
            Area area = namedParameterJdbcTemplate.queryForObject(
                    sql, mapSqlParameterSource, new BeanPropertyRowMapper<>(Area.class));
            return area;
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public List<Area> findByUnitId(UUID unitId)
    {
        final String sql = "SELECT * FROM area WHERE unit_id = :unitId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("unitId", unitId);
        List<Area> areas = this.namedParameterJdbcTemplate.query(
                sql,
                namedParameters,
                new BeanPropertyRowMapper<>(Area.class));
        return areas;
    }

}
