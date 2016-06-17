package org.ddouglascarr.command.member.requests;

public class CreateMemberRequest
{
    private String login;
    private String password;
    private String name;
    private String notifyEmail;

    public CreateMemberRequest() {}

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

    public void setLogin(String login)
    {
        this.login = login;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setNotifyEmail(String notifyEmail)
    {
        this.notifyEmail = notifyEmail;
    }
}
