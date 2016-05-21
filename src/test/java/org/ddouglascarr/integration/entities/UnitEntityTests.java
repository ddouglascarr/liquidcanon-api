package org.ddouglascarr.integration.entities;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.ddouglascarr.commandhandlers.CreateUnitHandler;
import org.ddouglascarr.commands.CreateUnit;
import org.ddouglascarr.entities.UnitEntity;
import org.ddouglascarr.events.UnitCreatedEvent;
import org.junit.Before;
import org.junit.Test;

public class UnitEntityTests
{
    private FixtureConfiguration fixture;

    @Before
    public void setup() throws Exception
    {
        fixture = Fixtures.newGivenWhenThenFixture(UnitEntity.class);
        CreateUnitHandler createUnitHandler = new CreateUnitHandler(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(createUnitHandler);
    }

    @Test
    public void createUnit() throws Exception
    {
        fixture.given()
                .when(new CreateUnit(new Long(8), "test", "test unit"))
                .expectEvents(new UnitCreatedEvent(new Long(8), "test", "test unit"));
    }
}
