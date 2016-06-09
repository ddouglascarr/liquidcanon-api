package org.ddouglascarr.command.member;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.ddouglascarr.command.member.events.AdminMemberCreatedEvent;

public class MemberEventHandler
{
    @EventHandler
    public void handle(AdminMemberCreatedEvent event)
    {
        System.out.println("Admin Member Created");
    }
}
