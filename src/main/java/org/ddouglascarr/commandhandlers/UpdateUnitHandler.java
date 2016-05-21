package org.ddouglascarr.commandhandlers;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.ddouglascarr.commands.UpdateUnit;
import org.ddouglascarr.entities.UnitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUnitHandler
{
    private Repository repository;

    @Autowired

    public UpdateUnitHandler(Repository repository)
    {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(UpdateUnit command)
    {
        UnitEntity unitEntity = (UnitEntity) repository.load(command.getId());
        unitEntity.update(command.getName(), command.getDescription());
    }

}
