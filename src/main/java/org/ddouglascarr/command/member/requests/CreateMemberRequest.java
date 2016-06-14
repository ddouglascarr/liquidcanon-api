package org.ddouglascarr.command.member.requests;

public class CreateMemberRequest
{
    private final String login;
    private final String password;
    private final String name;
    private final String notifyEmail;

    public CreateMemberRequest(String login, String password, String name, String notifyEmail)
    {
        this.login = login;
        this.password = password;
        this.name = name;
        this.notifyEmail = notifyEmail;
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
