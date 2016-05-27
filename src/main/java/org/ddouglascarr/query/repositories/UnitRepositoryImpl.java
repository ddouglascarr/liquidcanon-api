package org.ddouglascarr.query.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.query.models.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UnitRepositoryImpl implements UnitRepository
{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static String SELECT_LIST = String.join(" ",
            "id, parent_id, active, name, description, member_count, public_read");

    private BeanPropertyRowMapper<Unit> beanPropertyRowMapper =
            new BeanPropertyRowMapper<>(Unit.class);

    @Override
    public Unit findOneById(UUID id) throws ItemNotFoundException
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
