package org.ddouglascarr.commandhandlers;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.ddouglascarr.commands.CreateUnit;
import org.ddouglascarr.entities.UnitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUnitHandler
{
    @Autowired
    private Repository repository;

    @CommandHandler
    public void handle(CreateUnit createUnitCommand)
    {
        UnitEntity unitEntity = new UnitEntity(
                createUnitCommand.getName(), createUnitCommand.getDescription());

    }
}
