package org.ddouglascarr.controllers;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.ddouglascarr.command.unit.commands.CreateUnitCommand;
import org.ddouglascarr.utils.IdUtils;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Member;
import org.ddouglascarr.query.models.Unit;
import org.ddouglascarr.query.models.UserDetailsImpl;
import org.ddouglascarr.query.services.AreaService;
import org.ddouglascarr.query.services.MemberService;
import org.ddouglascarr.query.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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

    @Autowired
    private IdUtils idUtils;

    @RequestMapping(
            value = "/units/{unitId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Unit> getUnit(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @PathVariable UUID unitId)
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
    public ResponseEntity<List<Member>> getMembers(@PathVariable UUID unitId)
    {
        List<Member> members = memberService.findByUnitId(unitId);
        return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/units",
            method = RequestMethod.POST)
    public ResponseEntity<UUID> createUnit(
            @RequestBody CreateUnitRequest request)
    {
        UUID id = idUtils.generateUniqueId();
        CreateUnitCommand command = new CreateUnitCommand(
                id, request.getParentId(), request.getName(), request.getDescription());
        commandGateway.send(command);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    public static class CreateUnitRequest
    {
        private UUID parentId;
        private String name;
        private String description;

        public UUID getParentId()
        {
            return parentId;
        }

        public void setParentId(UUID parentId)
        {
            this.parentId = parentId;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getDescription()
        {
            return description;
        }

        public void setDescription(String description)
        {
            this.description = description;
        }
    }

}
