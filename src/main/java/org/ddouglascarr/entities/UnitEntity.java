package org.ddouglascarr.entities;

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

    public UnitEntity(String name, String description)
    {
        apply(new UnitCreatedEvent(name, description));
    }

    @EventSourcingHandler
    public void applyUnitCreation(UnitCreatedEvent unitCreatedEvent)
    {
        this.name = unitCreatedEvent.getName();
        this.description = unitCreatedEvent.getDescription();
        this.active = true;
        this.memberCount = new Long(0);
        System.out.println("Unit created: " + this.getName());
    }

    public void addMember()
    {
        apply(new MemberAddedEvent(this.getId()));
    }

    @EventSourcingHandler
    public void applyMemberAdded(MemberAddedEvent memberAddedEvent)
    {
        this.memberCount += 1;

    }

    public Long getId()
    {
        return id;
    }

    public Boolean getActive()
    {
        return active;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public Long getMemberCount()
    {
        return memberCount;
    }
}
