package org.ddouglascarr.query.models;

import org.ddouglascarr.enums.DelegationScope;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Delegation
{
    @Id
    private UUID id;

    private UUID trusterId;
    private UUID trusteeId;
    private DelegationScope scope;
    private UUID unitId;
    private UUID areaId;
    private UUID issueId;

    // Getters and Setters


    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public UUID getTrusterId()
    {
        return trusterId;
    }

    public void setTrusterId(UUID trusterId)
    {
        this.trusterId = trusterId;
    }

    public UUID getTrusteeId()
    {
        return trusteeId;
    }

    public void setTrusteeId(UUID trusteeId)
    {
        this.trusteeId = trusteeId;
    }

    public DelegationScope getScope()
    {
        return scope;
    }

    public void setScope(DelegationScope scope)
    {
        this.scope = scope;
    }

    public UUID getUnitId()
    {
        return unitId;
    }

    public void setUnitId(UUID unitId)
    {
        this.unitId = unitId;
    }

    public UUID getAreaId()
    {
        return areaId;
    }

    public void setAreaId(UUID areaId)
    {
        this.areaId = areaId;
    }

    public UUID getIssueId()
    {
        return issueId;
    }

    public void setIssueId(UUID issueId)
    {
        this.issueId = issueId;
    }
}
