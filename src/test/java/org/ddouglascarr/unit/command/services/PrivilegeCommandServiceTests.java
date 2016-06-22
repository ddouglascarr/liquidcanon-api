package org.ddouglascarr.unit.command.services;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.ddouglascarr.command.privilege.PrivilegeCommandService;
import org.ddouglascarr.command.privilege.PrivilegeCommandServiceImpl;
import org.ddouglascarr.command.privilege.requests.GrantPrivilegeRequest;
import org.ddouglascarr.exceptions.ConflictException;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.services.MemberService;
import org.ddouglascarr.query.services.PrivilegeService;
import org.ddouglascarr.query.services.UnitService;
import org.ddouglascarr.utils.IdUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.ddouglascarr.testutils.IntegrationTestConsts.*;

public class PrivilegeCommandServiceTests
{
    @Mock
    private PrivilegeService privilegeService;

    @Mock
    private MemberService memberService;

    @Mock
    private UnitService unitService;

    @Mock
    private IdUtils idUtils;

    @Mock
    private CommandGateway commandGateway;

    @InjectMocks
    private PrivilegeCommandService privilegeCommandService = new PrivilegeCommandServiceImpl();

    private GrantPrivilegeRequest request;
    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        request.setInitiativeRight(true);
        request.setPollingRight(true);
        request.setVotingRight(true);
        request.setUnitId(EARTH_UNIT_ID);
        request.setMemberId(HUGLE_MEMBER_ID);
    }

    @Test(expected = ItemNotFoundException.class)
    public void grantShouldThrowIfMemberDoesNotExist() throws Exception
    {
        request.setMemberId(NON_EXISTANT_MEMBER_ID);
        doThrow(new ItemNotFoundException()).when(memberService).findOne(NON_EXISTANT_MEMBER_ID);
        privilegeCommandService.grant(POITRAS_MEMBER_ID, request);
    }

    @Test
    public void grantShouldThrowIfUnitDoesNotExist() throws Exception
    {
        request.setUnitId(NON_EXISTENT_UNIT_ID);
        doThrow(new ItemNotFoundException()).when(unitService).findOne(NON_EXISTENT_UNIT_ID);
        privilegeCommandService.grant(POITRAS_MEMBER_ID, request);
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void grantShouldThrowIfMemberDoesNotHaveUnitAdminPrivileges() throws Exception
    {
        doThrow(new MemberUnprivilegedException()).when(privilegeService)
                .assertUnitAdminPrivilege(POITRAS_MEMBER_ID, EARTH_UNIT_ID);
        privilegeCommandService.grant(POITRAS_MEMBER_ID, request);
    }

    @Test(expected = ConflictException.class)
    public void grantShouldThrowIfThereIsAlreadyAPrivilegeForThatUnitAndMember() throws Exception
    {
        doThrow(new ConflictException()).when(privilegeService)
                .assertPrivilegeDoesNotExist(HUGLE_MEMBER_ID, EARTH_UNIT_ID);
        privilegeCommandService.grant(POITRAS_MEMBER_ID, request);
    }

    @Test
    public void grantShouldCallCommandGateway() throws Exception
    {
        
    }

}
