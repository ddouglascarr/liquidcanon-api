package org.ddouglascarr.command.unit;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.ddouglascarr.command.unit.commands.CreateUnitCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitCommandHandler
{
    private Repository<UnitAggregate> repository;

    @Autowired
    public UnitCommandHandler(Repository<UnitAggregate> repository)
    {
        this.repository = repository;
    }

    @CommandHandler
    public void handleCreate(CreateUnitCommand command)
    {
        UnitAggregate unitAggregate = new UnitAggregate(
                command.getId(), command.getParentId(), command.getName(),
                command.getDescription());
        repository.add(unitAggregate);
    }
}
