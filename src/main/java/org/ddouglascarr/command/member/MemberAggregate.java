package org.ddouglascarr.command.member;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.ddouglascarr.command.member.events.AdminMemberCreatedEvent;

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

    public MemberAggregate(UUID id, String login, String password, Boolean admin)
    {
        if(admin) {
            apply(new AdminMemberCreatedEvent(id, login, password));
        } else {

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
}
