package org.ddouglascarr.command.member;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.ddouglascarr.command.member.commands.CreateAdminMemberCommand;
import org.ddouglascarr.command.member.commands.CreateMemberCommand;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberCommandHandler
{
    private Repository<MemberAggregate> repository;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    public MemberCommandHandler(Repository<MemberAggregate> repository)
    {
        this.repository = repository;
    }

    @CommandHandler
    public void handleCreateAdminMember(CreateAdminMemberCommand command)
    {
        MemberAggregate memberAggregate = new MemberAggregate(
                null, command.getId(), command.getLogin(), command.getPassword(), command.getName(),
                command.getNotifyEmail(), true, dateUtils.generateCurrentDate());
        repository.add(memberAggregate);
    }

    @CommandHandler
    public void handleCreateMember(CreateMemberCommand command)
    {
        MemberAggregate memberAggregate = new MemberAggregate(
                command.getRequestingMemberId(), command.getId(), command.getLogin(), command.getPassword(), command.getName(),
                command.getNotifyEmail(), false, dateUtils.generateCurrentDate());
        repository.add(memberAggregate);
    }

}
