package org.ddouglascarr.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.UnitPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UnitPermissionRepositoryImpl implements UnitPermissionRepository
{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private BeanPropertyRowMapper<UnitPermission> beanPropertyRowMapper =
            new BeanPropertyRowMapper<>(UnitPermission.class);

    private static String SELECT_LIST = String.join(" ",
            "id, unit_id, public_read");

    @Override
    public UnitPermission findOne(Long id) throws ItemNotFoundException
    {
        String sql = String.join(" ",
                "SELECT", SELECT_LIST, "FROM  unit_permissions",
                "WHERE :id = id");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        try {
            UnitPermission unitPermission = namedParameterJdbcTemplate.queryForObject(
                    sql, mapSqlParameterSource, beanPropertyRowMapper);
            return unitPermission;
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public UnitPermission findOneByUnitId(Long unitId) throws ItemNotFoundException
    {
        String sql = String.join(" ",
                "SELECT", SELECT_LIST, "FROM  unit_permissions",
                "WHERE unit_id = :unitId");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("unitId", unitId);
        try {
            UnitPermission unitPermission = namedParameterJdbcTemplate.queryForObject(
                    sql, mapSqlParameterSource, beanPropertyRowMapper);
            return unitPermission;
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException();
        }

    }
}
