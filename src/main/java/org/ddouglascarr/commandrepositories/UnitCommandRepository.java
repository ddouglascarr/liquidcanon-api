package org.ddouglascarr.commandrepositories;

import org.ddouglascarr.events.UnitCreatedEvent;
import org.ddouglascarr.events.UnitUpdatedEvent;

public interface UnitCommandRepository
{
    void create(UnitCreatedEvent unitCreatedEvent);
    void update(UnitUpdatedEvent unitUpdatedEvent);
}
