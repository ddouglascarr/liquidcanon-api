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

    public CreateMemberCommand(UUID newId, CreateMemberCommand command)
    {
        if (null != command.getId()) throw new RuntimeException(
                "The UUID constructor is for adding an id to a command only");
        this.id = newId;
        this.requestingMemberId = command.getRequestingMemberId();
        this.login = command.getLogin();
        this.password = command.getPassword();
        this.name = command.getName();
        this.notifyEmail = command.getNotifyEmail();
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
