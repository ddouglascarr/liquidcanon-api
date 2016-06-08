package org.ddouglascarr.command.member;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.ddouglascarr.command.member.commands.CreateAdminMemberCommand;
import org.ddouglascarr.command.member.commands.CreateMemberCommand;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
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
                command.getId(), command.getLogin(), command.getPassword(), null, true);
        repository.add(memberAggregate);
    }

    @CommandHandler
    public void handleCreateMember(CreateMemberCommand command)
            throws MemberUnprivilegedException
    {
        MemberAggregate requester = repository.load(command.getRequestingMemberId());
        if (!requester.getAdmin()) {
            throw new MemberUnprivilegedException();
        }
        MemberAggregate memberAggregate = new MemberAggregate(
                command.getId(), command.getLogin(), command.getPassword(),
                command.getNotifyEmail(), false );
        repository.add(memberAggregate);
    }
}
