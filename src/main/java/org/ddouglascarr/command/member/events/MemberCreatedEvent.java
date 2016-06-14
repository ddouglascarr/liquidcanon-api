package org.ddouglascarr.command.member.events;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.Date;
import java.util.UUID;

public class MemberCreatedEvent
{
    private final UUID requestingMemberId;

    private final UUID id;
    private final String login;
    private final String password;
    private final String name;
    private final String notifyEmail;
    private final Date activated;

    public MemberCreatedEvent(UUID requestingMemberId, UUID id, String login, String password, String name, String notifyEmail, Date activated)
    {
        this.requestingMemberId = requestingMemberId;
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.notifyEmail = notifyEmail;
        this.activated = activated;
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

    public Date getActivated()
    {
        return activated;
    }
}

