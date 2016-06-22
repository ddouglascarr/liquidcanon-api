package org.ddouglascarr.command.privilege;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.ddouglascarr.command.privilege.commands.GrantPrivilegeCommand;
import org.ddouglascarr.command.privilege.events.PrivilegeGrantedEvent;
import org.ddouglascarr.query.models.Privilege;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PrivilegeAggregate extends AbstractAnnotatedAggregateRoot
{
    @AggregateIdentifier
    private UUID id;

    private UUID memberId;
    private UUID unitId;

    private Boolean unitManager = false;
    private Boolean areaManager = false;
    private Boolean memberManager = false;
    private Boolean initiativeRight;
    private Boolean pollingRight;
    private Boolean votingRight;

    public PrivilegeAggregate() {}

    @CommandHandler
    public PrivilegeAggregate(GrantPrivilegeCommand command)
    {
        apply(new PrivilegeGrantedEvent(command.getRequestingMemberId(), command.getId(),
                command.getMemberId(), command.getUnitId(), command.getPollingRight(),
                command.getVotingRight(), command.getInitiativeRight()));
    }

    @EventSourcingHandler
    public void handle(PrivilegeGrantedEvent event)
    {
        this.id = event.getId();
        this.memberId = event.getMemberId();
        this.unitId = event.getUnitId();
        this.votingRight = event.getVotingRight();
        this.pollingRight = event.getPollingRight();
        this.initiativeRight = event.getInitiativeRight();
    }
}
