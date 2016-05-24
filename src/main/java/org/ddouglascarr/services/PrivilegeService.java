package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;

public interface PrivilegeService
{
    void assertUnitReadPrivilege(Long memberId, Long unitId)
            throws MemberUnprivilegedException, ItemNotFoundException;
    void assertUnitVotingPrivilege(Long memberId, Long unitId)
            throws MemberUnprivilegedException;
}
