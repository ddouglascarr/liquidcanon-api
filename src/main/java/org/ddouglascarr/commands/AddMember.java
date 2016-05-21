package org.ddouglascarr.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class AddMember
{
    @TargetAggregateIdentifier
    private final Long unitId;


    public AddMember(Long unitId)
    {
        this.unitId = unitId;
    }

    public Long getUnitId()
    {
        return unitId;
    }
}
