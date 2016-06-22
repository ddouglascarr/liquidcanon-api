package org.ddouglascarr.command.privilege.events;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

public class PrivilegeGrantedEvent
{
    @TargetAggregateIdentifier
    private final UUID id;
    private final UUID requestingMemberId;
    private final UUID memberId;
    private final UUID unitId;
    private final Boolean pollingRight;
    private final Boolean votingRight;

    public PrivilegeGrantedEvent(UUID requestingMemberId, UUID id, UUID memberId, UUID unitId, Boolean pollingRight, Boolean votingRight)
    {
        this.requestingMemberId = requestingMemberId;
        this.id = id;
        this.memberId = memberId;
        this.unitId = unitId;
        this.pollingRight = pollingRight;
        this.votingRight = votingRight;
    }

    public UUID getId()
    {
        return id;
    }

    public UUID getRequestingMemberId()
    {
        return requestingMemberId;
    }

    public UUID getMemberId()
    {
        return memberId;
    }

    public UUID getUnitId()
    {
        return unitId;
    }

    public Boolean getPollingRight()
    {
        return pollingRight;
    }

    public Boolean getVotingRight()
    {
        return votingRight;
    }
}
