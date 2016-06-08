package org.ddouglascarr.command.member.events;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

public class AdminMemberCreatedEvent
{
    private final UUID id;
    private final String login;
    private final String password;

    public AdminMemberCreatedEvent(UUID id, String login, String password)
    {
        this.id = id;
        this.login = login;
        this.password = password;
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
}
