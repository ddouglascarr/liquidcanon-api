package org.ddouglascarr.models;

import org.ddouglascarr.enums.DelegationScopes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "delegation")
public class Delegation
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "truster_id") private Long trusterId;
    @Column(name = "trustee_id") private Long trusteeId;
    @Column(name = "scope") private DelegationScopes scopes;
    @Column(name = "unit_id") private Long unitId;
    @Column(name = "area_id") private Long areaId;
    @Column(name = "issue_id") private Long issueId;

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

    public DelegationScopes getScopes()
    {
        return scopes;
    }

    public void setScopes(DelegationScopes scopes)
    {
        this.scopes = scopes;
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
