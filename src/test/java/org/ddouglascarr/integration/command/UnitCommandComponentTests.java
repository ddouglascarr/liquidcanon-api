package org.ddouglascarr.integration.command;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.ddouglascarr.command.unit.UnitAggregate;
import org.ddouglascarr.command.unit.commands.CreateUnitCommand;
import org.ddouglascarr.command.unit.events.UnitCreatedEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class UnitCommandComponentTests
{
    private FixtureConfiguration fixture;

    final UUID ADMIN_MEMBER_ID = UUID.randomUUID();
    final UUID UNIT_ID = UUID.randomUUID();
    final String UNIT_NAME = "test";
    final String UNIT_DESCRIPTION = "test unit";

    @Before
    public void setup()
    {
        fixture = Fixtures.newGivenWhenThenFixture(UnitAggregate.class);
    }

    @Test
    public void createUnitShouldCreateUnit() throws Exception
    {
        fixture.given()
                .when(new CreateUnitCommand(ADMIN_MEMBER_ID, UNIT_ID, null, UNIT_NAME, UNIT_DESCRIPTION))
                .expectEvents(new UnitCreatedEvent(ADMIN_MEMBER_ID, UNIT_ID, null, UNIT_NAME, UNIT_DESCRIPTION));
    }

}
