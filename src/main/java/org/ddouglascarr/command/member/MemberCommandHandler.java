package org.ddouglascarr.command.member;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.ddouglascarr.command.member.commands.CreateAdminMemberCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberCommandHandler
{
    private Repository<MemberAggregate> repository;

    @Autowired
    public MemberCommandHandler(Repository<MemberAggregate> repository)
    {
        this.repository = repository;
    }

    @CommandHandler
    public void handleCreateAdminMember(CreateAdminMemberCommand command)
    {
        MemberAggregate memberAggregate = new MemberAggregate(
                command.getId(), command.getLogin(), command.getPassword(), true);
        repository.add(memberAggregate);
    }
}
