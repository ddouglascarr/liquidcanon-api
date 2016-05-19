package org.ddouglascarr.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.org.apache.xpath.internal.operations.Bool;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.Date;
import java.util.List;

public class Member
{
    @Id
    private Long id;

    @JsonIgnore
    private String password;
    private String login;

    private String name;
    private Boolean admin;
    private String notifyEmail;
    private Boolean active;
    private Date lastActivity;
    private Date lastLogin;
    private Boolean locked;
    private String organizationalUnit;
    private String realname;
    private Date birthday;
    private String email;
    private String xmppAddress;
    private String website;
    private String phone;
    private String mobilePhone;
    private String profession;
    private String externalMemberships;
    private String externalPosts;
    private String formattingEngine;
    private String statement;

    @ReadOnlyProperty
    private List<Unit> units;

    public Member()
    {}

    // used by UserDetailsImpl
    public Member(Member member)
    {
        this.id = member.id;
        this.login = member.login;
        this.name = member.name;
        this.admin = member.admin;
        this.lastActivity = member.lastActivity;
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

    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    public List<Unit> getUnits()
    {
        return units;
    }

    public void setUnits(List<Unit> units)
    {
        this.units = units;
    }

    public Date getLastActivity()
    {
        return lastActivity;
    }

    public void setLastActivity(Date lastActivity)
    {
        this.lastActivity = lastActivity;
    }

    public String getNotifyEmail()
    {
        return notifyEmail;
    }

    public void setNotifyEmail(String notifyEmail)
    {
        this.notifyEmail = notifyEmail;
    }

    public Date getLastLogin()
    {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin)
    {
        this.lastLogin = lastLogin;
    }

    public Boolean getLocked()
    {
        return locked;
    }

    public void setLocked(Boolean locked)
    {
        this.locked = locked;
    }

    public String getOrganizationalUnit()
    {
        return organizationalUnit;
    }

    public void setOrganizationalUnit(String organizationalUnit)
    {
        this.organizationalUnit = organizationalUnit;
    }

    public String getRealname()
    {
        return realname;
    }

    public void setRealname(String realname)
    {
        this.realname = realname;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getXmppAddress()
    {
        return xmppAddress;
    }

    public void setXmppAddress(String xmppAddress)
    {
        this.xmppAddress = xmppAddress;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getMobilePhone()
    {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }

    public String getProfession()
    {
        return profession;
    }

    public void setProfession(String profession)
    {
        this.profession = profession;
    }

    public String getExternalMemberships()
    {
        return externalMemberships;
    }

    public void setExternalMemberships(String externalMemberships)
    {
        this.externalMemberships = externalMemberships;
    }

    public String getExternalPosts()
    {
        return externalPosts;
    }

    public void setExternalPosts(String externalPosts)
    {
        this.externalPosts = externalPosts;
    }

    public String getFormattingEngine()
    {
        return formattingEngine;
    }

    public void setFormattingEngine(String formattingEngine)
    {
        this.formattingEngine = formattingEngine;
    }

    public String getStatement()
    {
        return statement;
    }

    public void setStatement(String statement)
    {
        this.statement = statement;
    }
}
