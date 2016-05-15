package org.ddouglascarr.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepository
{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private BeanPropertyRowMapper<Member> beanPropertyRowMapper =
            new BeanPropertyRowMapper<>(Member.class);

    private static String SELECT_LIST = String.join(" ",
            "m.id, m.password_liquidcanon AS password, m.login,",
            "m.name, m.admin, m.notify_email, m.active, m.last_activity" );

    @Override
    public Member findOneById(Long id) throws ItemNotFoundException
    {
        final String sql = String.join(" ",
                "SELECT", SELECT_LIST, "FROM member AS m WHERE id = :id");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        try {
            Member member = namedParameterJdbcTemplate.queryForObject(
                    sql, mapSqlParameterSource, beanPropertyRowMapper);
            return member;
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public Member findOneByLogin(String login) throws ItemNotFoundException
    {
        final String sql = String.join(" ",
                "SELECT", SELECT_LIST, "FROM member AS m WHERE login = :login");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("login", login);
        try {
            Member member = namedParameterJdbcTemplate.queryForObject(
                    sql, mapSqlParameterSource, beanPropertyRowMapper);
            return member;
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public List<Member> findByUnitId(Long unitId)
    {
        String sql = String.join(" ",
                "SELECT", SELECT_LIST, "FROM",
                "(SELECT * FROM unit WHERE unit.id = :unitId) AS u",
                "JOIN privilege AS p on u.id = p.unit_id",
                "JOIN member AS m ON m.id = p.member_id");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("unitId", unitId);
        List<Member> members = namedParameterJdbcTemplate.query(
                sql, mapSqlParameterSource, beanPropertyRowMapper);
        return members;
    }
}
