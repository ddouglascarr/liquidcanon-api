package org.ddouglascarr.command.member;

import org.ddouglascarr.command.member.commands.CreateAdminMemberCommand;
import org.ddouglascarr.command.member.commands.CreateMemberCommand;

import java.util.UUID;

public interface MemberCommandService
{
    UUID create(CreateMemberCommand command);
}
