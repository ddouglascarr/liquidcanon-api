package org.ddouglascarr.command.unit.requests;

import java.util.UUID;

public class CreateUnitRequest
{
    private UUID parentId;
    private String name;
    private String description;

    public UUID getParentId()
    {
        return parentId;
    }

    public void setParentId(UUID parentId)
    {
        this.parentId = parentId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}

