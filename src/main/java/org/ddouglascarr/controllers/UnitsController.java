package org.ddouglascarr.controllers;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.ddouglascarr.commands.CreateUnit;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class UnitsController
{
    @Autowired
    private UnitService unitService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private CommandGateway commandGateway;

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
            value = "/units",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<Long> createUnit(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CreateUnitRequest createUnitRequest)
    {
        Random random = new Random();
        Long id = new Long(random.nextInt());
        System.out.println("new id is " + id.toString());
        CreateUnit command = new CreateUnit(id,
                createUnitRequest.getName(), createUnitRequest.getDescription());
        commandGateway.send(command);
        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
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
