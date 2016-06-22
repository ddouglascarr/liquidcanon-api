package org.ddouglascarr.command.privilege.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.ddouglascarr.command.privilege.requests.GrantPrivilegeRequest;
import org.ddouglascarr.command.unit.commands.CreateUnitCommand;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class GrantPrivilegeCommand
{
    @TargetAggregateIdentifier
    private final UUID id;

    @NotNull private final UUID requestingMemberId;
    @NotNull private final UUID memberId;
    @NotNull private final UUID unitId;

    @NotNull private final Boolean pollingRight;
    @NotNull private final Boolean votingRight;
    @NotNull private final Boolean initiativeRight;

    public GrantPrivilegeCommand(UUID requestingMemberId, UUID id, GrantPrivilegeRequest request)
    {
        this.id = id;
        this.requestingMemberId = requestingMemberId;
        this.memberId = request.getMemberId();
        this.unitId = request.getUnitId();
        this.pollingRight = request.getPollingRight();
        this.votingRight = request.getVotingRight();
        this.initiativeRight = request.getInitiativeRight();
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

    public Boolean getInitiativeRight()
    {
        return initiativeRight;
    }
}
