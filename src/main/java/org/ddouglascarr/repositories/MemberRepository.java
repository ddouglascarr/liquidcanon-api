package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daniel on 17/04/16.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
{
    Member findOneById(Long id);
    Member findOneByLogin(String login);
}
