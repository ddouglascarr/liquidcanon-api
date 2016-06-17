package org.ddouglascarr.query.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.query.models.Member;

import java.util.List;
import java.util.UUID;

/**
 * Created by daniel on 17/04/16.
 */
public interface MemberRepository
{
    Member findOneById(UUID id) throws ItemNotFoundException;
    Member findOneByUnitIdAndId(UUID unitId, UUID id) throws ItemNotFoundException;
    Member findOneByLogin(String login) throws ItemNotFoundException;
    List<Member> findByUnitId(UUID unitId);
    Boolean isAtLeastOneAdminMember();

    void create(Member member);
    void update(Member member);

}
