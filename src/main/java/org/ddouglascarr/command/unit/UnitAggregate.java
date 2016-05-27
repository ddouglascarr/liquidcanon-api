package org.ddouglascarr.command.unit;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.ddouglascarr.command.unit.events.UnitCreatedEvent;

import java.util.UUID;

public class UnitAggregate extends AbstractAnnotatedAggregateRoot
{
    @AggregateIdentifier
    private UUID id;
    private UUID parentId;
    private String name;
    private String description;

    private Long memberCount;

    public UnitAggregate() {}

    public UnitAggregate(UUID id, UUID parentId, String name, String description)
    {
        apply(new UnitCreatedEvent(id, parentId, name, description));
    }

    @EventSourcingHandler
    public void applyUnitCreatedEvent(UnitCreatedEvent event)
    {
        this.id = event.getId();
        this.parentId = event.getParentId();
        this.name = event.getName();
        this.description = event.getDescription();
    }
}
