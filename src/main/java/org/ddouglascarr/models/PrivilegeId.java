package org.ddouglascarr.models;

import java.io.Serializable;

// Required because Privilege does not have a primary key
public class PrivilegeId implements Serializable
{
    private Long memberId;
    private Long unitId;

    PrivilegeId() {}

    public Long getMemberId()
    {
        return memberId;
    }

    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    public Long getUnitId()
    {
        return unitId;
    }

    public void setUnitId(Long unitId)
    {
        this.unitId = unitId;
    }
}
