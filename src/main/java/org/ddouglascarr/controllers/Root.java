package org.ddouglascarr.controllers;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.ddouglascarr.commands.AddMember;
import org.ddouglascarr.commands.CreateUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Root
{

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(
            value="/",
            method= RequestMethod.GET
    )
    @Transactional
    @ResponseBody
    public ResponseEntity<String> getMember()
    {
        CreateUnit createUnit = new CreateUnit(new Long(6), "foo", "foobar bingbong");
        commandGateway.send(createUnit);
        return new ResponseEntity<String>("Hello World", HttpStatus.OK);
    }

}
