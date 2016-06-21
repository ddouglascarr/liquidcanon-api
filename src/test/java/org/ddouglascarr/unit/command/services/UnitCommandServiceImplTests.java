package org.ddouglascarr.unit.command.services;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.ddouglascarr.command.unit.UnitCommandService;
import org.ddouglascarr.command.unit.UnitCommandServiceImpl;
import org.ddouglascarr.command.unit.commands.CreateUnitCommand;
import org.ddouglascarr.exceptions.ConflictException;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Member;
import org.ddouglascarr.query.models.Unit;
import org.ddouglascarr.query.services.MemberService;
import org.ddouglascarr.query.services.UnitService;
import org.ddouglascarr.utils.IdUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.ddouglascarr.testutils.IntegrationTestConsts.*;

public class UnitCommandServiceImplTests
{
    @Mock
    private MemberService memberService;

    @Mock
    private UnitService unitService;

    @Mock
    private CommandGateway commandGateway;

    @Mock
    private IdUtils idUtils;
    @InjectMocks
    private UnitCommandService unitCommandService = new UnitCommandServiceImpl();

    @Mock Member mockMember;
    @Mock Member adminMember;
    @Mock Unit mockUnit;

    @Before
    public void setup() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        when(memberService.findOne(POITRAS_MEMBER_ID)).thenReturn(mockMember);
        when(memberService.findOne(ADMIN_MEMBER_ID)).thenReturn(adminMember);
        when(mockMember.getAdmin()).thenReturn(false);
        when(adminMember.getAdmin()).thenReturn(true);
        when(idUtils.generateUniqueId()).thenReturn(EARTH_UNIT_ID);
        when(unitService.findOne(EARTH_MOON_FEDERATION_UNIT_ID)).thenReturn(mockUnit);
    }

    @Test(expected = ItemNotFoundException.class)
    public void createShouldThrowIfParendDoesNotExist() throws Exception
    {
        when(unitService.findOne(EARTH_MOON_FEDERATION_UNIT_ID)).thenReturn(null);
        unitCommandService.create(ADMIN_MEMBER_ID, EARTH_MOON_FEDERATION_UNIT_ID, EARTH_UNIT_NAME,
                EARTH_UNIT_DESCRIPTION);
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void createShouldThrowIfMemberIsNotAdmin() throws Exception
    {
        unitCommandService.create(POITRAS_MEMBER_ID, EARTH_MOON_FEDERATION_UNIT_ID, EARTH_UNIT_NAME,
                EARTH_UNIT_DESCRIPTION);
    }

    @Test
    public void createShouldIssueCommand() throws Exception
    {
        ArgumentCaptor<CreateUnitCommand> argument =
                ArgumentCaptor.forClass(CreateUnitCommand.class);

        UUID newUnitId = unitCommandService.create(ADMIN_MEMBER_ID, EARTH_MOON_FEDERATION_UNIT_ID,
                EARTH_UNIT_NAME, EARTH_UNIT_DESCRIPTION);

        assertEquals(EARTH_UNIT_ID, newUnitId);
        verify(commandGateway).send(argument.capture());
        CreateUnitCommand command = argument.getValue();
        assertEquals(ADMIN_MEMBER_ID, command.getRequestingMemberId());
        assertEquals(EARTH_UNIT_ID, command.getId());
        assertEquals(EARTH_UNIT_NAME, command.getName());
        assertEquals(EARTH_UNIT_DESCRIPTION, command.getDescription());
    }

    @Test
    public void createShouldNotFailIfNullParent() throws Exception
    {
        UUID newUnitId = unitCommandService.create(ADMIN_MEMBER_ID, null,
                EARTH_UNIT_NAME, EARTH_UNIT_DESCRIPTION);
        assertEquals(EARTH_UNIT_ID, newUnitId);
    }
}
