package org.ddouglascarr.models;

import java.util.UUID;

public class Privilege
{
    private UUID memberId;
    private UUID unitId;

    private Boolean unitManager;
    private Boolean areaManager;
    private Boolean memberManager;
    private Boolean initiativeRight;
    private Boolean pollingRight;
    private Boolean votingRight;

    // Getters and setters

    public UUID getMemberId()
    {
        return memberId;
    }

    public void setMemberId(UUID memberId)
    {
        this.memberId = memberId;
    }

    public UUID getUnitId()
    {
        return unitId;
    }

    public void setUnitId(UUID unitId)
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
