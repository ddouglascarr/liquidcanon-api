package org.ddouglascarr.entities;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.EventSourcedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.ddouglascarr.commands.CreateUnit;
import org.ddouglascarr.events.MemberAddedEvent;
import org.ddouglascarr.events.UnitCreatedEvent;

import java.util.UUID;

public class UnitEntity extends AbstractAnnotatedAggregateRoot
{
    @AggregateIdentifier
    private Long id;

    private Boolean active;
    private String name;
    private String description;

    private Long memberCount;

    public UnitEntity() {}

    /*public UnitEntity(Long id, String name, String description)
    {
        apply(new UnitCreatedEvent(id, name, description));
    }*/

    public UnitEntity(CreateUnit command)
    {
        apply(new UnitCreatedEvent(command.getId(), command.getName(), command.getDescription()));
    }

    @EventSourcingHandler
    public void applyUnitCreatedEvent(UnitCreatedEvent event)
    {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        System.out.println("UnitCreatedEvent handled: " + id);
    }



}
