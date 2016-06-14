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
    private final String name;
    private final String notifyEmail;

    public CreateMemberCommand(UUID requestingMemberId, UUID id, String login, String password, String name, String notifyEmail)
    {
        this.requestingMemberId = requestingMemberId;
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
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

    public String getName()
    {
        return name;
    }

    public String getNotifyEmail()
    {
        return notifyEmail;
    }
}
