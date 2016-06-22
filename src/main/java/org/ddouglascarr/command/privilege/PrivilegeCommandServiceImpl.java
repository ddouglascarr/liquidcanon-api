package org.ddouglascarr.command.privilege;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.ddouglascarr.command.privilege.commands.GrantPrivilegeCommand;
import org.ddouglascarr.command.privilege.requests.GrantPrivilegeRequest;
import org.ddouglascarr.exceptions.ConflictException;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Privilege;
import org.ddouglascarr.query.services.MemberService;
import org.ddouglascarr.query.services.PrivilegeService;
import org.ddouglascarr.query.services.UnitService;
import org.ddouglascarr.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PrivilegeCommandServiceImpl implements PrivilegeCommandService
{
    @Autowired
    PrivilegeService privilegeService;

    @Autowired
    MemberService memberService;

    @Autowired
    UnitService unitService;

    @Autowired
    IdUtils idUtils;

    @Autowired
    CommandGateway commandGateway;

    @Override
    public UUID grant(UUID requestingMemberId, GrantPrivilegeRequest request)
            throws MemberUnprivilegedException, ItemNotFoundException, ConflictException
    {
        /*
        UUID id = idUtils.generateUniqueId();
        privilegeService.assertPrivilegeDoesNotExist(
                request.getMemberId(), request.getUnitId());
        privilegeService.assertUnitAdminPrivilege(requestingMemberId, request.getUnitId());
        memberService.findOne(request.getMemberId()); // throws if no member
        unitService.findOne(request.getUnitId()); // throws if no unit
        commandGateway.send(new GrantPrivilegeCommand(requestingMemberId, id, request));
        return id;*/
        return null;
    }
}
