package org.ddouglascarr.command.unit;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.ddouglascarr.command.unit.commands.CreateUnitCommand;
import org.ddouglascarr.command.unit.events.UnitCreatedEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UnitAggregate extends AbstractAnnotatedAggregateRoot
{
    @AggregateIdentifier
    private UUID id;
    private UUID parentId;
    private String name;
    private String description;

    private Long memberCount;

    public UnitAggregate() {}

    @CommandHandler
    public UnitAggregate(CreateUnitCommand command)
    {
        apply(new UnitCreatedEvent(command.getRequestingMemberId(), command.getId(),
                command.getParentId(), command.getName(), command.getDescription()));
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
