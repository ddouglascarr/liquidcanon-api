package org.ddouglascarr.command.unit.events;

import java.util.UUID;

public class UnitCreatedEvent
{
    private final UUID requestingMemberId;
    private final UUID id;
    private final UUID parentId;
    private final String name;
    private final String description;

    public UnitCreatedEvent(UUID requestingMemberId, UUID id, UUID parentId, String name, String description)
    {
        this.requestingMemberId = requestingMemberId;
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.description = description;
    }

    public UUID getRequestingMemberId()
    {
        return requestingMemberId;
    }

    public UUID getId() { return id; }

    public UUID getParentId()
    {
        return parentId;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

}
