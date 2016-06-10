package org.ddouglascarr.query.eventhandlers;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.ddouglascarr.command.member.events.AdminMemberCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class MemberEventHandler
{
    @EventHandler
    public void handle(AdminMemberCreatedEvent event)
    {
        System.out.println("Admin Member Created");
    }
}
