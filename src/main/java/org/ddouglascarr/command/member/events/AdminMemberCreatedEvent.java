package org.ddouglascarr.command.member.events;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.Date;
import java.util.UUID;

public class AdminMemberCreatedEvent
{
    private final UUID id;
    private final String login;
    private final String password;
    private final String name;
    private final String notifyEmail;
    private final Date activated;

    public AdminMemberCreatedEvent(UUID id, String login, String password, String name, String notifyEmail, Date activated)
    {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.notifyEmail = notifyEmail;
        this.activated = activated;
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
