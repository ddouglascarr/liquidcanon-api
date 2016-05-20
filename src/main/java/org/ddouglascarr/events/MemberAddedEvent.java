package org.ddouglascarr.events;

public class MemberAddedEvent
{
    private final Long unitId;

    public MemberAddedEvent(Long unitId)
    {
        this.unitId = unitId;
    }

    public Long getUnitId()
    {
        return unitId;
    }
}
