package org.ddouglascarr.query.listeners;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.ddouglascarr.command.member.events.AdminMemberCreatedEvent;
import org.ddouglascarr.command.member.events.MemberCreatedEvent;
import org.ddouglascarr.query.models.Member;
import org.ddouglascarr.query.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberQueryEventListener
{
    @Autowired
    private MemberService memberService;

    @EventHandler
    public void handle(AdminMemberCreatedEvent event)
    {
        Member member = new Member();
        member.setId(event.getId());
        member.setLogin(event.getLogin());
        member.setPassword(event.getPassword());
        member.setName(event.getName());
        member.setNotifyEmail(event.getNotifyEmail());
        member.setAdmin(true);
        member.setActive(true);
        member.setActivated(event.getActivated());
        member.setLastActivity(event.getActivated());

        memberService.create(member);
    }

    @EventHandler
    public void handle(MemberCreatedEvent event)
    {
        Member member = new Member();
        member.setId(event.getId());
        member.setLogin(event.getLogin());
        member.setPassword(event.getPassword());
        member.setName(event.getName());
        member.setNotifyEmail(event.getNotifyEmail());
        member.setAdmin(false);
        member.setActive(true);
        member.setActivated(event.getActivated());
        member.setLastActivity(event.getActivated());

        // memberService.create(member);
    }
}
