package org.ddouglascarr.query.services;

import org.ddouglascarr.exceptions.ConflictException;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Privilege;

import java.util.UUID;

public interface PrivilegeService
{
    void assertUnitReadPrivilege(UUID memberId, UUID unitId)
            throws MemberUnprivilegedException, ItemNotFoundException;
    void assertUnitVotingPrivilege(UUID memberId, UUID unitId)
            throws MemberUnprivilegedException;
    void assertUnitAdminPrivilege(UUID memberId, UUID unitId)
            throws MemberUnprivilegedException;
    void assertPrivilegeDoesNotExist(UUID memberId, UUID unitId)
            throws ConflictException;
}
