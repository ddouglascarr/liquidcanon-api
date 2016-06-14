package org.ddouglascarr.command.member;

import org.axonframework.repository.Repository;
import org.ddouglascarr.command.member.commands.CreateAdminMemberCommand;
import org.ddouglascarr.command.member.commands.CreateMemberCommand;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class MemberCommandServiceImpl implements MemberCommandService
{
    @Autowired
    private Repository<MemberAggregate> repository;

    @Override
    public UUID create(CreateMemberCommand command)
    {
        return null;
    }
}
