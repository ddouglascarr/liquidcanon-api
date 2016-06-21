package org.ddouglascarr.query.listeners;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.ddouglascarr.command.unit.events.UnitCreatedEvent;
import org.ddouglascarr.query.models.Unit;
import org.ddouglascarr.query.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitQueryEventListener
{
    @Autowired
    private UnitService unitService;

    @EventHandler
    public void handle(UnitCreatedEvent event)
    {
        Unit unit = new Unit();
        unit.setId(event.getId());
        unit.setParentId(event.getParentId());
        unit.setName(event.getName());
        unit.setDescription(event.getDescription());
        unitService.create(unit);
    }
}
