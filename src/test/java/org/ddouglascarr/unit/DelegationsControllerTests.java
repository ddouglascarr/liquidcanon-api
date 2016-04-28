package org.ddouglascarr.unit;

import org.apache.catalina.connector.Response;
import org.ddouglascarr.controllers.DelegationsController;
import org.ddouglascarr.models.Delegation;
import org.ddouglascarr.models.UserDetailsImpl;
import org.ddouglascarr.services.DelegationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DelegationsControllerTests
{
    @Mock
    private DelegationService delegationService;

    @InjectMocks
    private DelegationsController delegationsController;

    // NOTE: If a user is looking up another member's delegation, their id is USER_DETAILS_ID
    private static Long MEMBER_ID = new Long(1);
    private static Long USER_DETAILS_ID = new Long(6);
    private static Long UNIT_ID = new Long(2);
    private static Long DELEGATION_ID = new Long(3);
    private static Long TRUSTER_ID = new Long(4);
    private static Long TRUSTEE_ID = new Long(5);

    @Mock
    Delegation mockDelegation;
    @Mock
    UserDetailsImpl userDetails;

    List<Delegation> mockDelegationList;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        when(userDetails.getId()).thenReturn(USER_DETAILS_ID);
        when(mockDelegation.getId()).thenReturn(DELEGATION_ID);
        mockDelegationList = new ArrayList<Delegation>();
    }

    @Test
    public void getOutgoingDelegationsReturnsTrusterDelegationsForMember() throws Exception
    {
        when(delegationService.findByTrusterId(USER_DETAILS_ID, UNIT_ID, MEMBER_ID))
                .thenReturn(mockDelegationList);
        ResponseEntity<List<Delegation>> resp = delegationsController
                .getOutgoingDelegations(userDetails, UNIT_ID, MEMBER_ID);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        List<Delegation> returnedList = resp.getBody();
        assertEquals(mockDelegationList, returnedList);
    }

    @Test
    public void getIncomingDelegationsReturnsTrusteeDelegationsForMember() throws Exception
    {
        when(delegationService.findByTrusteeId(USER_DETAILS_ID, UNIT_ID, MEMBER_ID))
                .thenReturn(mockDelegationList);
        ResponseEntity<List<Delegation>> resp = delegationsController
                .getIncomingDelegations(userDetails, UNIT_ID, MEMBER_ID);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        List<Delegation> returnedList = resp.getBody();
        assertEquals(mockDelegationList, returnedList);
    }
}
