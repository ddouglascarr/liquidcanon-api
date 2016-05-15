package org.ddouglascarr.models;

public class Privilege
{
    private Long memberId;
    private Long unitId;

    private Boolean unitManager;
    private Boolean areaManager;
    private Boolean memberManager;
    private Boolean initiativeRight;
    private Boolean pollingRight;
    private Boolean votingRight;

    // Getters and setters

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


    public Boolean getVotingRight()
    {
        return votingRight;
    }

    public void setVotingRight(Boolean votingRight)
    {
        this.votingRight = votingRight;
    }

    public Boolean getUnitManager()
    {
        return unitManager;
    }

    public void setUnitManager(Boolean unitManager)
    {
        this.unitManager = unitManager;
    }

    public Boolean getAreaManager()
    {
        return areaManager;
    }

    public void setAreaManager(Boolean areaManager)
    {
        this.areaManager = areaManager;
    }

    public Boolean getMemberManager()
    {
        return memberManager;
    }

    public void setMemberManager(Boolean memberManager)
    {
        this.memberManager = memberManager;
    }

    public Boolean getInitiativeRight()
    {
        return initiativeRight;
    }

    public void setInitiativeRight(Boolean initiativeRight)
    {
        this.initiativeRight = initiativeRight;
    }

    public Boolean getPollingRight()
    {
        return pollingRight;
    }

    public void setPollingRight(Boolean pollingRight)
    {
        this.pollingRight = pollingRight;
    }
}
