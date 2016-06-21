package org.ddouglascarr.query.listeners;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.ddouglascarr.command.unit.events.UnitCreatedEvent;
import org.ddouglascarr.query.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;

public class UnitQueryEventListener
{
    @Autowired
    private UnitService unitService;

    @EventHandler
    public void handle(UnitCreatedEvent event)
    {

    }
}
