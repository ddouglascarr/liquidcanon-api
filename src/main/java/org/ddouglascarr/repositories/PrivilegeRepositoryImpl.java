package org.ddouglascarr.repositories;


import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PrivilegeRepositoryImpl implements PrivilegeRepository
{
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Privilege findOneByMemberIdAndUnitId(Long memberId, Long unitId)
            throws ItemNotFoundException
    {
        String sql = String.join(" ",
                "SELECT * FROM privilege",
                "WHERE member_id = :memberId AND unit_id = :unitId");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("memberId", memberId);
        namedParameters.addValue("unitId", unitId);
        try {
            Privilege privilege = (Privilege) namedParameterJdbcTemplate.queryForObject(
                    sql, namedParameters, new BeanPropertyRowMapper<Privilege>(Privilege.class));
            return privilege;
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException();
        }
    }
}
