package org.ddouglascarr.command.privilege;

import org.ddouglascarr.command.privilege.requests.GrantPrivilegeRequest;
import org.ddouglascarr.exceptions.ConflictException;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;

import java.util.UUID;

public interface PrivilegeCommandService
{
    UUID grant(UUID requestingMemberId, GrantPrivilegeRequest request)
            throws MemberUnprivilegedException, ItemNotFoundException, ConflictException;

}

