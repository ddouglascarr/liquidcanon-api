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

    @InjectMocks
    private DelegationService delegationService = new DelegationServiceImpl();

    private static Long MEMBER_ID = new Long(1);
    private static Long UNIT_ID = new Long(2);
    private static Long TRUSTER_ID = new Long(3);
    private static Long TRUSTEE_ID = new Long(4);
    private static Long AREA_ID = new Long(5);
    private static Long DELEGATION_ID = new Long(6);

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
}
