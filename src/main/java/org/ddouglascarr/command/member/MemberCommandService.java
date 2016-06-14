package org.ddouglascarr.command.member;

import org.ddouglascarr.command.member.commands.CreateAdminMemberCommand;
import org.ddouglascarr.command.member.commands.CreateMemberCommand;
import org.ddouglascarr.command.member.requests.CreateMemberRequest;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;

import java.util.UUID;

public interface MemberCommandService
{
    UUID create(UUID requestingMemberId, String login, String password, String name,
                String notifyEmail) throws MemberUnprivilegedException;
}
