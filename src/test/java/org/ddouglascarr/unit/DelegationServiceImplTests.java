package org.ddouglascarr.unit;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Delegation;
import org.ddouglascarr.repositories.DelegationRepository;
import org.ddouglascarr.services.DelegationService;
import org.ddouglascarr.services.DelegationServiceImpl;
import org.ddouglascarr.services.PrivilegeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DelegationServiceImplTests
{
    @Mock
    private DelegationRepository delegationRepository;

    @Mock
    private PrivilegeService privilegeService;

    @InjectMocks
    private DelegationService delegationService = new DelegationServiceImpl();

    private final Long TRUSTEE_ID = new Long(1);
    private final Long TRUSTER_ID = new Long(2);
    private final Long DELEGATION_ID = new Long(3);
    private final Long MEMBER_ID = new Long(4);
    private final Long UNIT_ID = new Long(5);

    @Mock
    private Delegation mockDelegation;
    private List<Delegation> mockDelegationList;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        when(mockDelegation.getId()).thenReturn(DELEGATION_ID);
        mockDelegationList = new ArrayList<>();
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void findOneByIdShouldThrowIfNoReadPrivilege() throws Exception
    {
        doThrow(new MemberUnprivilegedException()).when(privilegeService)
                .assertUnitReadPrivilege(MEMBER_ID, UNIT_ID);
        delegationService.findOneById(MEMBER_ID, UNIT_ID, DELEGATION_ID);
    }

    @Test(expected = ItemNotFoundException.class)
    public void findOneByIdShouldThrowIfNotFound() throws Exception
    {
        when(delegationRepository.findOneById(UNIT_ID, DELEGATION_ID))
                .thenReturn(null);
        delegationService.findOneById(MEMBER_ID, UNIT_ID, DELEGATION_ID);
    }

    @Test
    public void findOneByIdShouldReturnDelegation() throws Exception
    {
        when(delegationRepository.findOneById(UNIT_ID, DELEGATION_ID))
                .thenReturn(mockDelegation);
        Delegation returnedDelegation = delegationService
                .findOneById(MEMBER_ID, UNIT_ID, DELEGATION_ID);
        assertEquals(mockDelegation, returnedDelegation);
        verify(privilegeService, times(1)).assertUnitReadPrivilege(MEMBER_ID, UNIT_ID);
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void findByUnitIdAndTrusterIdShouldThrowIfNoReadPrivilege() throws Exception
    {
        doThrow(new MemberUnprivilegedException()).when(privilegeService)
                .assertUnitReadPrivilege(MEMBER_ID, UNIT_ID);
        delegationService.findByTrusterId(MEMBER_ID, UNIT_ID, TRUSTER_ID);
    }

    @Test
    public void findByUnitIdAndTruserIdShouldReturnEmptyIfNoDelegations() throws Exception
    {
        when(delegationRepository.findByTrusterId(UNIT_ID, TRUSTER_ID))
                .thenReturn(mockDelegationList);
        List<Delegation> returnedDelegations = delegationService
                .findByTrusterId(MEMBER_ID, UNIT_ID, TRUSTER_ID);
        assertEquals(mockDelegationList, returnedDelegations);
        verify(privilegeService, times(1)).assertUnitReadPrivilege(MEMBER_ID, UNIT_ID);
    }

    @Test
    public void findByTrusterIdShouldReturnListOfDelegations() throws Exception
    {
        mockDelegationList.add(mockDelegation);
        when(delegationRepository.findByTrusterId(UNIT_ID, TRUSTER_ID))
                .thenReturn(mockDelegationList);
        List<Delegation> returnedDelegations = delegationService
                .findByTrusterId(MEMBER_ID, UNIT_ID, TRUSTER_ID);
        assertEquals(mockDelegationList, returnedDelegations);
        verify(privilegeService, times(1)).assertUnitReadPrivilege(MEMBER_ID, UNIT_ID);
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void findByTrusteeIdShouldThrowIfNoUnitReadPrivilege() throws Exception
    {
        doThrow(new MemberUnprivilegedException()).when(privilegeService)
                .assertUnitReadPrivilege(MEMBER_ID, UNIT_ID);
        delegationService.findByTrusteeId(MEMBER_ID, UNIT_ID, TRUSTEE_ID);
    }

    @Test
    public void findByTrusteShouldReturnListOfDelegations() throws Exception
    {
        mockDelegationList.add(mockDelegation);
        when(delegationRepository.findUnitDelegationsByTrusteeId(UNIT_ID, TRUSTEE_ID))
                .thenReturn(mockDelegationList);
        List<Delegation> returnedDelegations = delegationService
                .findByTrusteeId(MEMBER_ID, UNIT_ID, TRUSTEE_ID);
        assertEquals(mockDelegationList, returnedDelegations);
        verify(privilegeService, times(1)).assertUnitReadPrivilege(MEMBER_ID, UNIT_ID);
    }




}
