package org.ddouglascarr.commandhandlers;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.ddouglascarr.commands.CreateUnit;
import org.ddouglascarr.commands.UpdateUnit;
import org.ddouglascarr.entities.UnitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitCommandHandler
{
    private Repository<UnitEntity> repository;

    @Autowired
    public UnitCommandHandler(Repository<UnitEntity> repository)
    {
        this.repository = repository;
    }

    @CommandHandler
    public void handleCreate(CreateUnit command)
    {
        UnitEntity unitEntity = new UnitEntity(command.getId(), command.getName(), command.getDescription());
        repository.add(unitEntity);
    }

    @CommandHandler
    public void handleUpdateUnit(UpdateUnit command)
    {
        UnitEntity unitEntity = (UnitEntity) repository.load(command.getId());
        unitEntity.update(command.getName(), command.getDescription());
    }
}
