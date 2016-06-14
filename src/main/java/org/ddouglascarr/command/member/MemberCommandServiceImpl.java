package org.ddouglascarr.command.member;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.repository.Repository;
import org.ddouglascarr.command.member.commands.CreateMemberCommand;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class MemberCommandServiceImpl implements MemberCommandService
{
    @Autowired
    private Repository<MemberAggregate> repository;

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private IdUtils idUtils;

    @Override
    public UUID create(CreateMemberCommand command) throws MemberUnprivilegedException
    {
        MemberAggregate requestingMember = repository.load(command.getRequestingMemberId());
        if (!requestingMember.getAdmin()) throw new MemberUnprivilegedException();
        UUID id = idUtils.generateUniqueId();
        commandGateway.send(new CreateMemberCommand(id, command));
        return id;
    }

}
