package org.ddouglascarr.models;

import org.ddouglascarr.enums.DelegationScope;
import org.springframework.data.annotation.Id;

public class Delegation
{
    @Id
    private Long id;

    private Long trusterId;
    private Long trusteeId;
    private DelegationScope scope;
    private Long unitId;
    private Long areaId;
    private Long issueId;

    // Getters and Setters


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getTrusterId()
    {
        return trusterId;
    }

    public void setTrusterId(Long trusterId)
    {
        this.trusterId = trusterId;
    }

    public Long getTrusteeId()
    {
        return trusteeId;
    }

    public void setTrusteeId(Long trusteeId)
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

    public Long getUnitId()
    {
        return unitId;
    }

    public void setUnitId(Long unitId)
    {
        this.unitId = unitId;
    }

    public Long getAreaId()
    {
        return areaId;
    }

    public void setAreaId(Long areaId)
    {
        this.areaId = areaId;
    }

    public Long getIssueId()
    {
        return issueId;
    }

    public void setIssueId(Long issueId)
    {
        this.issueId = issueId;
    }
}
