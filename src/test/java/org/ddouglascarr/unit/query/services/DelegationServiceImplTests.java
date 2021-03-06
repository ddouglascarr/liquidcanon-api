package org.ddouglascarr.unit.query.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Delegation;
import org.ddouglascarr.query.repositories.DelegationRepository;
import org.ddouglascarr.query.services.DelegationService;
import org.ddouglascarr.query.services.DelegationServiceImpl;
import org.ddouglascarr.query.services.PrivilegeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DelegationServiceImplTests
{
    @Mock
    private DelegationRepository delegationRepository;

    @Mock
    private PrivilegeService privilegeService;

    @Mock
    private Delegation mockDelegation;

    @Mock
    private List<Delegation> mockDelegationList;

    @InjectMocks
    private DelegationService delegationService = new DelegationServiceImpl();

    private static UUID MEMBER_ID = UUID.randomUUID();
    private static UUID UNIT_ID = UUID.randomUUID();
    private static UUID TRUSTER_ID = UUID.randomUUID();
    private static UUID TRUSTEE_ID = UUID.randomUUID();
    private static UUID AREA_ID = UUID.randomUUID();
    private static UUID DELEGATION_ID = UUID.randomUUID();

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        when(mockDelegation.getId()).thenReturn(DELEGATION_ID);
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void findUnitDelegationForTrusterShouldThrowIfMemberNotUnitReadPrivileged()
            throws Exception
    {
        doThrow(new MemberUnprivilegedException()).when(privilegeService)
                .assertUnitReadPrivilege(MEMBER_ID, UNIT_ID);
        delegationService.findUnitDelegationForTruster(MEMBER_ID, UNIT_ID, TRUSTER_ID);
    }

    @Test(expected = ItemNotFoundException.class)
    public void findUnitDelegationForTrusterShouldThrowIfNoDelegation()
            throws Exception
    {
        when(delegationRepository.findUnitDelegationByTrusterId(UNIT_ID, TRUSTER_ID))
                .thenReturn(null);
        delegationService.findUnitDelegationForTruster(MEMBER_ID, UNIT_ID, TRUSTER_ID);
    }

    @Test
    public void findUnitDelegationForTrusterShouldReturnDelegation()
            throws Exception
    {
        when(delegationRepository.findUnitDelegationByTrusterId(UNIT_ID, TRUSTER_ID))
                .thenReturn(mockDelegation);
        Delegation returnedDelegation = delegationService
                .findUnitDelegationForTruster(MEMBER_ID, UNIT_ID, TRUSTER_ID);
        assertEquals(mockDelegation, returnedDelegation);
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void findIncomingUnitDelegationForTrusteeShouldThrowIfNotUnitReadPrivileged()
            throws Exception
    {
        doThrow(new MemberUnprivilegedException()).when(privilegeService)
                .assertUnitReadPrivilege(MEMBER_ID, UNIT_ID);
        delegationService.findIncomingUnitDelegationForTrustee(MEMBER_ID, UNIT_ID, TRUSTEE_ID);
    }

    @Test
    public void findIncomingUnitDelegationForTrusteeShouldReturnListOfDelegations()
            throws Exception
    {
        when(delegationRepository.findUnitDelegationsByTrusteeId(UNIT_ID, TRUSTEE_ID))
                .thenReturn(mockDelegationList);
        List<Delegation> returnedList = delegationService
                .findIncomingUnitDelegationForTrustee(MEMBER_ID, UNIT_ID, TRUSTEE_ID);
        assertEquals(mockDelegationList, returnedList);
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void findAreaDelegationForTrusterShouldThrowIfNotReadPrivileged()
            throws Exception
    {
        doThrow(new MemberUnprivilegedException()).when(privilegeService)
                .assertUnitReadPrivilege(MEMBER_ID, UNIT_ID);
        delegationService.findAreaDelegationForTruster(MEMBER_ID, UNIT_ID, AREA_ID, TRUSTER_ID);
    }

    @Test(expected = ItemNotFoundException.class)
    public void findAreaDelegationForTrusterShouldThrowIfNoDelegation()
            throws Exception
    {
        when(delegationRepository.findAreaDelegationByTrusterId(UNIT_ID, AREA_ID, TRUSTER_ID))
                .thenReturn(null);
        delegationService.findAreaDelegationForTruster(MEMBER_ID, UNIT_ID, AREA_ID, TRUSTER_ID);
    }

    @Test
    public void findAreaDelegationForTrusterShouldReturnDelegation()
            throws Exception
    {
        when(delegationRepository.findAreaDelegationByTrusterId(UNIT_ID, AREA_ID, TRUSTER_ID))
                .thenReturn(mockDelegation);
        Delegation returnedDelegation = delegationService
                .findAreaDelegationForTruster(MEMBER_ID, UNIT_ID, AREA_ID, TRUSTER_ID);
        assertEquals(mockDelegation, returnedDelegation);
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void findIncomingAreaDelegationsForTrusteeShouldThrowIfMemberUnprivileged()
            throws Exception
    {
        doThrow(new MemberUnprivilegedException()).when(privilegeService)
                .assertUnitReadPrivilege(MEMBER_ID, UNIT_ID);
        delegationService.findIncomingAreaDelegationsForTrustee(
                MEMBER_ID, UNIT_ID, AREA_ID, TRUSTEE_ID);
    }

    @Test
    public void findIncomingAreaDelegationsForTrusteeShouldReturnListOfDelegations()
            throws Exception
    {
        when(delegationRepository.findAreaDelegationsByTrusteeId(UNIT_ID, AREA_ID, TRUSTEE_ID))
                .thenReturn(mockDelegationList);
        List<Delegation> returnedList = delegationService
                .findIncomingAreaDelegationsForTrustee(MEMBER_ID, UNIT_ID, AREA_ID, TRUSTEE_ID);
        assertEquals(mockDelegationList, returnedList);
    }

}
