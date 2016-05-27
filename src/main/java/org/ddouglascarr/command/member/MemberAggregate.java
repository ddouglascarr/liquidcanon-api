package org.ddouglascarr.command.member;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.ddouglascarr.command.member.events.AdminMemberCreatedEvent;
import org.ddouglascarr.command.member.events.MemberCreatedEvent;

import java.util.Date;
import java.util.UUID;

public class MemberAggregate extends AbstractAnnotatedAggregateRoot
{
    @AggregateIdentifier
    private UUID id;

    private String password;
    private String login;

    private String name;
    private Boolean admin;
    private String notifyEmail;
    private Boolean active;
    private Date activated;
    private String identification;
    private Date lastActivity;
    private Date lastLogin;
    private Boolean locked;
    private String organizationalUnit;
    private String internalPosts;
    private String realname;
    private String birthday;
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

    public MemberAggregate() {}

    public MemberAggregate(UUID id, String login, String password, String notifyEmail, Boolean admin)
    {
        if(admin) {
            apply(new AdminMemberCreatedEvent(id, login, password));
        } else {
            apply(new MemberCreatedEvent(id, login, password, notifyEmail));
        }
    }

    @EventSourcingHandler
    public void applyAdminMemberCreatedEvent(AdminMemberCreatedEvent event)
    {
        this.admin = true;
        this.id = event.getId();
        this.login = event.getLogin();
        this.password = event.getPassword();
    }

    @EventSourcingHandler
    public void applyMemberCreatedEvent(MemberCreatedEvent event)
    {
        this.admin = false;
        this.id = event.getId();
        this.login = event.getLogin();
        this.password = event.getPassword();
        this.notifyEmail = event.getNotifyEmail();
    }

    // Getters
    public UUID getId()
    {
        return id;
    }

    public String getPassword()
    {
        return password;
    }

    public String getLogin()
    {
        return login;
    }

    public String getName()
    {
        return name;
    }

    public Boolean getAdmin()
    {
        return admin;
    }

    public String getNotifyEmail()
    {
        return notifyEmail;
    }

    public Boolean getActive()
    {
        return active;
    }

    public Date getActivated()
    {
        return activated;
    }

    public String getIdentification()
    {
        return identification;
    }

    public Date getLastActivity()
    {
        return lastActivity;
    }

    public Date getLastLogin()
    {
        return lastLogin;
    }

    public Boolean getLocked()
    {
        return locked;
    }

    public String getOrganizationalUnit()
    {
        return organizationalUnit;
    }

    public String getInternalPosts()
    {
        return internalPosts;
    }

    public String getRealname()
    {
        return realname;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public String getEmail()
    {
        return email;
    }

    public String getXmppAddress()
    {
        return xmppAddress;
    }

    public String getWebsite()
    {
        return website;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getMobilePhone()
    {
        return mobilePhone;
    }

    public String getProfession()
    {
        return profession;
    }

    public String getExternalMemberships()
    {
        return externalMemberships;
    }

    public String getExternalPosts()
    {
        return externalPosts;
    }

    public String getFormattingEngine()
    {
        return formattingEngine;
    }

    public String getStatement()
    {
        return statement;
    }
}
