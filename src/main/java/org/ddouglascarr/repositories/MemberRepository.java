package org.ddouglascarr.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Member;

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
