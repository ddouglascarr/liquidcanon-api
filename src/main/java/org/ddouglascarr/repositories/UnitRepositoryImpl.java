package org.ddouglascarr.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UnitRepositoryImpl implements UnitRepository
{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static String SELECT_LIST = String.join(" ",
            "id, parent_id, active, name, description, member_count");

    private BeanPropertyRowMapper<Unit> beanPropertyRowMapper =
            new BeanPropertyRowMapper<>(Unit.class);

    @Override
    public Unit findOneById(Long id) throws ItemNotFoundException
    {
        String sql = String.join(" ",
                "SELECT", SELECT_LIST, "FROM unit",
                "WHERE id = :id");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        try {
            Unit unit = namedParameterJdbcTemplate.queryForObject(
                    sql, mapSqlParameterSource, beanPropertyRowMapper);
            return unit;
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException();
        }
    }
}
