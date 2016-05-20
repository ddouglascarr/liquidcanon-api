package org.ddouglascarr.events;

public class UnitCreatedEvent
{
    private final String name;
    private final String description;

    public UnitCreatedEvent(String name, String description)
    {
        this.name = name;
        this.description = description;
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
