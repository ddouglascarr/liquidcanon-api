package org.ddouglascarr.commandrepositories;

import org.ddouglascarr.events.UnitCreatedEvent;
import org.ddouglascarr.events.UnitUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UnitCommandRepositoryImpl implements UnitCommandRepository
{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void update(UnitUpdatedEvent unitUpdatedEvent)
    {
        String sql = String.join(" ",
                "UPDATE unit",
                "SET name = :name, description = :description",
                "WHERE id = :id");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", unitUpdatedEvent.getId());
        mapSqlParameterSource.addValue("name", unitUpdatedEvent.getName());
        mapSqlParameterSource.addValue("description", unitUpdatedEvent.getDescription());
        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public void create(UnitCreatedEvent unitCreatedEvent)
    {
        String sql = String.join(" ",
                "INSERT INTO unit",
                "(id, name, description)",
                "VALUES (:id, :name, :description)");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", unitCreatedEvent.getId());
        mapSqlParameterSource.addValue("name", unitCreatedEvent.getName());
        mapSqlParameterSource.addValue("description", unitCreatedEvent.getDescription());
        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }
}
