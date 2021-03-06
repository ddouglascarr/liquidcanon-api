package org.ddouglascarr.command.member.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.ddouglascarr.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class CreateAdminMemberCommand
{
    @TargetAggregateIdentifier
    private final UUID id;
    private final String login;
    private final String password;
    private final String name;
    private final String notifyEmail;

    public CreateAdminMemberCommand(UUID id, String login, String password, String name, String notifyEmail)
    {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
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

    public String getName()
    {
        return name;
    }

    public String getNotifyEmail()
    {
        return notifyEmail;
    }
}
