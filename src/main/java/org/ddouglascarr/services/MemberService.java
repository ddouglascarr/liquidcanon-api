package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Member;

import java.util.List;

/**
 * Created by daniel on 17/04/16.
 */
public interface MemberService
{
    Member findOne(Long id) throws ItemNotFoundException;
    Member findOneByUnitIdAndId(Long userDetailsId, Long unitId, Long id)
            throws ItemNotFoundException, MemberUnprivilegedException;
    Member findOneByLogin(String login) throws ItemNotFoundException;
    List<Member> findByUnitId(Long unitId);
}
