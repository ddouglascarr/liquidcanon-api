package org.ddouglascarr.commandhandlers;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.ddouglascarr.commands.CreateUnit;
import org.springframework.stereotype.Component;

@Component
public class CreateUnitHandler
{
    @CommandHandler
    public void handle(CreateUnit createUnitCommand)
    {
        System.out.println("Handling the CreateUnit command");
        System.out.println(createUnitCommand.getName());
        System.out.println(createUnitCommand.getDescription());

    }
}
