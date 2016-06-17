package org.ddouglascarr.command.unit;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.ddouglascarr.command.unit.commands.CreateUnitCommand;
import org.ddouglascarr.exceptions.ConflictException;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Member;
import org.ddouglascarr.query.services.MemberService;
import org.ddouglascarr.query.services.UnitService;
import org.ddouglascarr.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class UnitCommandServiceImpl implements UnitCommandService
{
    @Autowired
    private MemberService memberService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private IdUtils idUtils;

    @Override
    public UUID create(UUID requestingMemberId, UUID parentId, String name, String description)
            throws MemberUnprivilegedException, ItemNotFoundException
    {
        UUID id = idUtils.generateUniqueId();
        if (null == unitService.findOne(parentId)) throw new ItemNotFoundException();
        Member requestingMember = memberService.findOne(requestingMemberId);
        if (!requestingMember.getAdmin()) throw new MemberUnprivilegedException();
        commandGateway.send(new CreateUnitCommand(requestingMemberId, id, parentId,
                name, description));
        return id;
    }
}
