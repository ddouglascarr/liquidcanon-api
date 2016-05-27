package org.ddouglascarr.command.member.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

public class CreateAdminMemberCommand
{
    @TargetAggregateIdentifier
    private final UUID id;
    private final String login;
    private final String password;

    public CreateAdminMemberCommand(UUID id, String login, String password)
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
