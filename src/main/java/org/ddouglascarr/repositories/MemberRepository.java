package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by daniel on 17/04/16.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
{
    Member findOneById(Long id);
    Member findOneByLogin(String login);

    @Query(
            nativeQuery = true,
            value = "SELECT m.name FROM (SELECT * FROM unit WHERE unit.id = :unitId) AS u JOIN privilege AS p on u.id = p.unit_id JOIN member AS m ON m.id = p.member_id"
    )
    List<Member> findByUnitId(Long unitId);
}
