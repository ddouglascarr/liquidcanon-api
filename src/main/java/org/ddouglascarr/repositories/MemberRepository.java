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
    Member findOneByUnitIdAndId(Long unitId, Long id) throws ItemNotFoundException;
    Member findOneByLogin(String login) throws ItemNotFoundException;
    List<Member> findByUnitId(Long unitId);
}
