package org.ddouglascarr.config;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.ddouglascarr.command.member.commands.CreateAdminMemberCommand;
import org.ddouglascarr.query.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent>
{
    @Autowired
    private MemberService memberService;

    @Autowired
    private CommandGateway commandGateway;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent)
    {
        System.out.println("applicationReadyEvent");
        if (!memberService.isAtLeastOneAdminMember()) {
            CreateAdminMemberCommand command = new CreateAdminMemberCommand(
                    UUID.randomUUID(), "admin", "password", "Default Admin", null );
            commandGateway.send(command);
        }
    }
}
