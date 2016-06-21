package org.ddouglascarr.unit.query.listeners;

import org.ddouglascarr.command.unit.events.UnitCreatedEvent;
import org.ddouglascarr.query.listeners.UnitQueryEventListener;
import org.ddouglascarr.query.models.Unit;
import org.ddouglascarr.query.services.UnitService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.ddouglascarr.testutils.IntegrationTestConsts.*;

public class UnitQueryEventListenerTests
{
    @Mock
    private UnitService unitService;

    @InjectMocks
    private UnitQueryEventListener listener = new UnitQueryEventListener();

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void handleUnitCreateEvent() throws Exception
    {
        UnitCreatedEvent event = new UnitCreatedEvent(
                ADMIN_MEMBER_ID, EARTH_UNIT_ID, EARTH_MOON_FEDERATION_UNIT_ID, EARTH_UNIT_NAME,
                EARTH_UNIT_DESCRIPTION);
        ArgumentCaptor<Unit> argument = ArgumentCaptor.forClass(Unit.class);

        listener.handle(event);
        verify(unitService).create(argument.capture());
        Unit unit = argument.getValue();
        assertEquals(EARTH_UNIT_ID, unit.getId());
        assertEquals(EARTH_MOON_FEDERATION_UNIT_ID, unit.getParentId());
        assertEquals(EARTH_UNIT_NAME, unit.getName());
        assertEquals(EARTH_UNIT_DESCRIPTION, unit.getDescription());
    }
}
