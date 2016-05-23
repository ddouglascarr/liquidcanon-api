package org.ddouglascarr.eventhandlers;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.ddouglascarr.commandrepositories.UnitCommandRepository;
import org.ddouglascarr.events.UnitCreatedEvent;
import org.ddouglascarr.events.UnitUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitEventHandler
{

    @Autowired
    UnitCommandRepository unitCommandRepository;

    @EventHandler
    public void handleUnitCreatedEvent(UnitCreatedEvent event)
    {
        unitCommandRepository.create(event);
    }

    @EventHandler
    public void handleUnitUpdatedEvent(UnitUpdatedEvent event)
    {
        unitCommandRepository.update(event);
    }
}
