package org.ddouglascarr.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by daniel on 17/04/16.
 */
public interface MemberRepository
{
    Member findOneById(Long id) throws ItemNotFoundException;
    Member findOneByLogin(String login) throws ItemNotFoundException;

    @Query(
            nativeQuery = true,
            value = "SELECT m.* FROM (SELECT * FROM unit WHERE unit.id = :unitId) AS u JOIN privilege AS p on u.id = p.unit_id JOIN member AS m ON m.id = p.member_id"
    )
    List<Member> findByUnitId(Long unitId);
}
