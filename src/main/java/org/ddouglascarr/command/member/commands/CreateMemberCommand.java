package org.ddouglascarr.command.member.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

public class CreateMemberCommand
{
    private final UUID requestingMemberId;

    @TargetAggregateIdentifier
    private final UUID id;
    private final String login;
    private final String password;
    private final String notifyEmail;

    public CreateMemberCommand(UUID requestingMemberId, UUID id, String login,
                               String password, String notifyEmail)
    {
        this.id = id;
        this.requestingMemberId = requestingMemberId;
        this.login = login;
        this.password = password;
        this.notifyEmail = notifyEmail;
    }

    public UUID getRequestingMemberId()
    {
        return requestingMemberId;
    }

    public UUID getId()
    {
        return id;
    }

    public String getLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }

    public String getNotifyEmail()
    {
        return notifyEmail;
    }
}
