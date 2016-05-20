package org.ddouglascarr.commands;

public class AddMember
{
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
