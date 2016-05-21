package org.ddouglascarr.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class UpdateUnit
{
    @TargetAggregateIdentifier
    private final Long id;
    private final String name;
    private final String description;

    public UpdateUnit(Long id, String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId()
    {
        return id;
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
