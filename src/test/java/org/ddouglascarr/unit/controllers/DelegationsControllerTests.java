package org.ddouglascarr.unit.controllers;


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
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DelegationsControllerTests
{
    @Mock
    DelegationService delegationService;

    @Mock
    private UserDetailsImpl userDetails;

    @Mock
    private Delegation mockDelegation;

    @InjectMocks
    DelegationsController delegationsController;

    private static UUID UNIT_ID = UUID.randomUUID();
    private static UUID AREA_ID = UUID.randomUUID();
    private static UUID MEMBER_ID = UUID.randomUUID();
    private static UUID DELEGATION_ID = UUID.randomUUID();
    private static UUID TRUSTER_ID = UUID.randomUUID();
    private static UUID TRUSTEE_ID = UUID.randomUUID();

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        when(userDetails.getId()).thenReturn(MEMBER_ID);
        when(mockDelegation.getId()).thenReturn(DELEGATION_ID);
    }

    @Test
    public void getUnitDelegationShouldReturnDelegation() throws Exception
    {
        when(delegationService.findUnitDelegationForTruster(MEMBER_ID, UNIT_ID, TRUSTER_ID))
                .thenReturn(mockDelegation);
        ResponseEntity<Delegation> resp = delegationsController.getOutgoingUnitDelegation(
                userDetails, UNIT_ID, TRUSTER_ID);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Delegation returnedDelegation = resp.getBody();
        assertEquals(mockDelegation, returnedDelegation);
    }

    @Test
    public void getIncomingUnitDelegationsShouldReturnListOfDelegations() throws Exception
    {
        List<Delegation> mockList = new ArrayList<>();
        mockList.add(mockDelegation);
        when(delegationService.findIncomingUnitDelegationForTrustee(MEMBER_ID, UNIT_ID, TRUSTEE_ID))
                .thenReturn(mockList);
        ResponseEntity<List<Delegation>> resp = delegationsController.getIncomingUnitDelegations(
                userDetails, UNIT_ID, TRUSTEE_ID);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals(mockList, resp.getBody());
    }

    @Test
    public void getOutgoingAreaDelegationShouldReturnDelegation() throws Exception
    {
        when(delegationService.findAreaDelegationForTruster(
                MEMBER_ID, UNIT_ID, AREA_ID, TRUSTER_ID))
                .thenReturn(mockDelegation);
        ResponseEntity<Delegation> resp = delegationsController
                .getOutgoingAreaDelegation(userDetails, UNIT_ID, AREA_ID, TRUSTER_ID);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals(mockDelegation, resp.getBody());
    }

    @Test
    public void getIncomingAreaDelegationsShouldReturnListOfDelegations() throws Exception
    {
        List<Delegation> mockList = new ArrayList<>();
        mockList.add(mockDelegation);
        when(delegationService.findIncomingAreaDelegationsForTrustee(
                MEMBER_ID, UNIT_ID, AREA_ID, TRUSTEE_ID))
                .thenReturn(mockList);
        ResponseEntity<List<Delegation>> resp = delegationsController
                .getIncomingAreaDelegations(userDetails, UNIT_ID, AREA_ID, TRUSTEE_ID);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals(mockList, resp.getBody());
    }
}
