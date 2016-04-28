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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/units/{unitId}/members/{memberId}/delegations")
public class DelegationsController
{
    @Autowired
    private DelegationService delegationService;

    @HandleServiceErrors
    @RequestMapping(
            value = "/outgoing",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<Delegation>> getOutgoingDelegations(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long unitId,
            @PathVariable Long memberId)
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        List<Delegation> delegations = delegationService
                .findByTrusterId(userDetails.getId(), unitId, memberId);
        return new ResponseEntity<>(delegations, HttpStatus.OK);
    }

    @HandleServiceErrors
    @RequestMapping(
            value = "/incoming",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<Delegation>> getIncomingDelegations(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long unitId,
            @PathVariable Long memberId)
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        List<Delegation> delegations = delegationService
                .findByTrusteeId(userDetails.getId(), unitId, memberId);
        return new ResponseEntity<>(delegations, HttpStatus.OK);
    }
}
