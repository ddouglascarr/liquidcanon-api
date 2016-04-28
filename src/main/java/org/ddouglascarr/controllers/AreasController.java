package org.ddouglascarr.controllers;

import org.ddouglascarr.aop.Foo;
import org.ddouglascarr.aop.HandleServiceErrors;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Area;
import org.ddouglascarr.models.UserDetailsImpl;
import org.ddouglascarr.services.AreaService;
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
@RequestMapping(value = "units/{unitId}/areas")
public class AreasController
{
    @Autowired
    private AreaService areaService;

    @HandleServiceErrors
    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<Area>> getAreas(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                               @PathVariable Long unitId)
            throws MemberUnprivilegedException, ItemNotFoundException
    {
        System.out.println("getAreas()");
        List<Area> areas = areaService.findByUnitId(userDetails.getId(), unitId);
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{areaId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Area> getArea(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @PathVariable Long unitId,
                                        @PathVariable Long areaId)
    {
        try {
            Area area = areaService.findOneByUnitId(userDetails.getId(), unitId, areaId);
            return new ResponseEntity<Area>(area, HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<Area>(e.getResponseHeaders(), HttpStatus.NOT_FOUND);
        } catch (MemberUnprivilegedException e) {
            return new ResponseEntity<Area>(e.getResponseHeaders(), HttpStatus.UNAUTHORIZED);
        }
    }

}
