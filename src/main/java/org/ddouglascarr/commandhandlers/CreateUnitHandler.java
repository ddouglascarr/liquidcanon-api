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
    private Repository repository;

    @Autowired
    public CreateUnitHandler(Repository repository)
    {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateUnit command)
    {
        UnitEntity unitEntity = new UnitEntity(command);
        repository.add(unitEntity);
    }
}
