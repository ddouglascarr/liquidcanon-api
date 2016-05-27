package org.ddouglascarr.query.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Member;

import java.util.List;
import java.util.UUID;

/**
 * Created by daniel on 17/04/16.
 */
public interface MemberService
{
    Member findOne(UUID id) throws ItemNotFoundException;
    Member findOneByUnitIdAndId(UUID userDetailsId, UUID unitId, UUID id)
            throws ItemNotFoundException, MemberUnprivilegedException;
    Member findOneByLogin(String login) throws ItemNotFoundException;
    List<Member> findByUnitId(UUID unitId);
}
