package org.ddouglascarr.eventhandlers;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.ddouglascarr.events.UnitCreatedEvent;

public class UnitEventHandler
{
    @EventHandler
    public void handle(UnitCreatedEvent event)
    {
        System.out.println("handleCreate unit event creation: " + event.getName());
    }
}
