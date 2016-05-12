package org.ddouglascarr.controllers;

import org.ddouglascarr.aop.HandleServiceErrors;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Delegation;
import org.ddouglascarr.models.UserDetailsImpl;
import org.ddouglascarr.services.DelegationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.jws.soap.SOAPBinding;

@RestController
@RequestMapping(value = "/units/{unitId}")
public class DelegationsController
{
    @Autowired
    private DelegationService delegationService;

    @HandleServiceErrors
    @RequestMapping(
            value = "/members/{trusterId}/delegation",
            method = RequestMethod.GET)
    public ResponseEntity<Delegation> getOutgoingUnitDelegation(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long unitId,
            @PathVariable Long trusterId)
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        Delegation delegation = delegationService.findUnitDelegationForTruster(
                userDetails.getId(), unitId, trusterId);
        return new ResponseEntity<>(delegation, HttpStatus.OK);
    }

    @HandleServiceErrors
    @RequestMapping(
            value = "/members/{trusteeId}/incoming-delegations",
            method = RequestMethod.GET)
    public ResponseEntity<List<Delegation>> getIncomingUnitDelegations(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long unitId,
            @PathVariable Long trusteeId)
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        List<Delegation> delegations = delegationService.findIncomingUnitDelegationForTrustee(
                userDetails.getId(), unitId, trusteeId);
        return new ResponseEntity<>(delegations, HttpStatus.OK);
    }

    @HandleServiceErrors
    @RequestMapping(
            value = "/areas/{areaId}/members/{trusterId}/delegation",
            method = RequestMethod.GET)
    public ResponseEntity<Delegation> getOutgoingAreaDelegation(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long unitId,
            @PathVariable Long areaId,
            @PathVariable Long trusterId)
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        Delegation delegation = delegationService.findAreaDelegationForTruster(
                userDetails.getId(), unitId, areaId, trusterId);
        return new ResponseEntity<>(delegation, HttpStatus.OK);
    }

    @HandleServiceErrors
    @RequestMapping(
            value = "/areas/{areaId}/members/{trusteeId}/incoming-delegations",
            method = RequestMethod.GET)
    public ResponseEntity<List<Delegation>> getIncomingAreaDelegations(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long unitId,
            @PathVariable Long areaId,
            @PathVariable Long trusteeId)
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        List<Delegation> delegations = delegationService.findIncomingAreaDelegationsForTrustee(
                userDetails.getId(), unitId, areaId, trusteeId);
        return new ResponseEntity<>(delegations, HttpStatus.OK);
    }

}
