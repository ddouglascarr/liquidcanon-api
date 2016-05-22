package org.ddouglascarr.integration.entities;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.ddouglascarr.commandhandlers.UnitCommandHandler;
import org.ddouglascarr.commands.CreateUnit;
import org.ddouglascarr.commands.UpdateUnit;
import org.ddouglascarr.entities.UnitEntity;
import org.ddouglascarr.events.UnitCreatedEvent;
import org.ddouglascarr.events.UnitUpdatedEvent;
import org.junit.Before;
import org.junit.Test;

public class UnitEntityTests
{
    private FixtureConfiguration fixture;

    @Before
    public void setup() throws Exception
    {
        fixture = Fixtures.newGivenWhenThenFixture(UnitEntity.class);
        UnitCommandHandler unitCommandHandler = new UnitCommandHandler(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(unitCommandHandler);
    }

    @Test
    public void createUnit() throws Exception
    {
        fixture.given()
                .when(new CreateUnit(new Long(8), "test", "test unit"))
                .expectEvents(new UnitCreatedEvent(new Long(8), "test", "test unit"));
    }

    @Test
    public void updateUnit() throws Exception
    {
        fixture.given(new UnitCreatedEvent(new Long(2), "old-name", "old-description"))
                .when(new UpdateUnit(new Long(2), "new-name", "new-description"))
                .expectEvents(new UnitUpdatedEvent(new Long(2), "new-name", "new-description"));

    }
}
