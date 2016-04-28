package org.ddouglascarr.controllers;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Area;
import org.ddouglascarr.models.Member;
import org.ddouglascarr.models.Unit;
import org.ddouglascarr.models.UserDetailsImpl;
import org.ddouglascarr.services.AreaService;
import org.ddouglascarr.services.MemberService;
import org.ddouglascarr.services.UnitService;
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
public class UnitsController
{
    @Autowired
    private UnitService unitService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private AreaService areaService;

    @RequestMapping(
            value = "/units/{unitId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Unit> getUnit(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @PathVariable Long unitId)
    {
        try {
            Unit unit = unitService.findOne(userDetails.getId(), unitId);
            return new ResponseEntity<Unit>(unit, HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<Unit>(e.getResponseHeaders(), HttpStatus.NOT_FOUND);
        } catch (MemberUnprivilegedException e) {
            return new ResponseEntity<Unit>(e.getResponseHeaders(), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(
            value = "/units/{unitId}/members",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<Member>> getMembers(@PathVariable Long unitId)
    {
        List<Member> members = memberService.findByUnitId(unitId);
        return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
    }

}
