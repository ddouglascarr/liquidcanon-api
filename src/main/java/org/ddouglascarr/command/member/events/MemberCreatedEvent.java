package org.ddouglascarr.command.member.events;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

public class MemberCreatedEvent
{
    @TargetAggregateIdentifier
    private final UUID id;
    private final String login;
    private final String password;
    private final String notifyEmail;

    public MemberCreatedEvent(UUID id, String login, String password, String notifyEmail)
    {
        this.id = id;
        this.login = login;
        this.password = password;
        this.notifyEmail = notifyEmail;
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
