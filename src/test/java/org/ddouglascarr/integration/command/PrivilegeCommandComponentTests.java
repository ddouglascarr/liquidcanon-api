package org.ddouglascarr.integration.command;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.ddouglascarr.command.privilege.PrivilegeAggregate;
import org.ddouglascarr.command.privilege.commands.GrantPrivilegeCommand;
import org.ddouglascarr.command.privilege.events.PrivilegeGrantedEvent;
import org.ddouglascarr.command.privilege.requests.GrantPrivilegeRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.ddouglascarr.testutils.IntegrationTestConsts.*;

public class PrivilegeCommandComponentTests
{
    private FixtureConfiguration fixture;

    private UUID PRIVILEGE_ID = UUID.randomUUID();

    @Before
    public void setup()
    {
        fixture = Fixtures.newGivenWhenThenFixture(PrivilegeAggregate.class);
    }

    @Test
    public void grantPrivilege() throws Exception
    {
        GrantPrivilegeRequest request = new GrantPrivilegeRequest();
        request.setMemberId(POITRAS_MEMBER_ID);
        request.setUnitId(EARTH_UNIT_ID);
        request.setPollingRight(true);
        request.setVotingRight(true);
        fixture.given()
                .when(new GrantPrivilegeCommand(ADMIN_MEMBER_ID, PRIVILEGE_ID, request))
                .expectEvents(new PrivilegeGrantedEvent(ADMIN_MEMBER_ID, PRIVILEGE_ID,
                        POITRAS_MEMBER_ID, EARTH_UNIT_ID, true, true));
    }

}
