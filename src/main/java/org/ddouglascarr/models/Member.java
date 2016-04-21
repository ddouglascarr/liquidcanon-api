package org.ddouglascarr.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member")
public class Member
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="password_liquidcanon")
    @JsonIgnore
    private String password;
    private String login;

    private String name;
    private Boolean admin;
    private String notify_email;
    private Boolean active;
    private Date last_activity;

    public Member()
    {}

    public Member(Member member)
    {
        this.id = member.id;
        this.login = member.login;
        this.name = member.name;
        this.admin = member.admin;
        this.last_activity = member.last_activity;
        this.active = member.active;
        this.password = member.password;
    }


    // Getters and Setters
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Boolean getAdmin()
    {
        return admin;
    }

    public void setAdmin(Boolean admin)
    {
        this.admin = admin;
    }

    public String getNotify_email()
    {
        return notify_email;
    }

    public void setNotify_email(String notify_email)
    {
        this.notify_email = notify_email;
    }

    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    public Date getLast_activity()
    {
        return last_activity;
    }

    public void setLast_activity(Date last_activity)
    {
        this.last_activity = last_activity;
    }
}
