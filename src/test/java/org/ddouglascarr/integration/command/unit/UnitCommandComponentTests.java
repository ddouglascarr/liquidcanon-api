package org.ddouglascarr.integration.command.unit;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.ddouglascarr.command.unit.UnitAggregate;
import org.ddouglascarr.command.unit.UnitCommandHandler;
import org.ddouglascarr.command.unit.commands.CreateUnitCommand;
import org.ddouglascarr.command.unit.events.UnitCreatedEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class UnitCommandComponentTests
{
    private FixtureConfiguration fixture;

    final UUID UNIT_ID = UUID.randomUUID();
    final String UNIT_NAME = "test";
    final String UNIT_DESCRIPTION = "test unit";

    @Before
    public void setup()
    {
        fixture = Fixtures.newGivenWhenThenFixture(UnitAggregate.class);
        UnitCommandHandler unitCommandHandler = new UnitCommandHandler(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(unitCommandHandler);
    }

    @Test
    public void createUnitShouldCreateUnit() throws Exception
    {
        fixture.given()
                .when(new CreateUnitCommand(UNIT_ID, null, UNIT_NAME, UNIT_DESCRIPTION))
                .expectEvents(new UnitCreatedEvent(UNIT_ID, null, UNIT_NAME, UNIT_DESCRIPTION));
    }

}
