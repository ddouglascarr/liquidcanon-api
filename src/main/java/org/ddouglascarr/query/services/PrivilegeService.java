package org.ddouglascarr.query.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;

import java.util.UUID;

public interface PrivilegeService
{
    void assertUnitReadPrivilege(UUID memberId, UUID unitId)
            throws MemberUnprivilegedException, ItemNotFoundException;
    void assertUnitVotingPrivilege(UUID memberId, UUID unitId)
            throws MemberUnprivilegedException;
}
