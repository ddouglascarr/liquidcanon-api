package org.ddouglascarr.command.privilege.requests;

import java.util.UUID;

public class GrantPrivilegeRequest
{
    private UUID memberId;
    private UUID unitId;
    private Boolean pollingRight;
    private Boolean votingRight;
    private Boolean initiativeRight;

    public GrantPrivilegeRequest() {}

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

    public Boolean getPollingRight()
    {
        return pollingRight;
    }

    public void setPollingRight(Boolean pollingRight)
    {
        this.pollingRight = pollingRight;
    }

    public Boolean getVotingRight()
    {
        return votingRight;
    }

    public void setVotingRight(Boolean votingRight)
    {
        this.votingRight = votingRight;
    }

    public Boolean getInitiativeRight()
    {
        return initiativeRight;
    }

    public void setInitiativeRight(Boolean initiativeRight)
    {
        this.initiativeRight = initiativeRight;
    }
}
