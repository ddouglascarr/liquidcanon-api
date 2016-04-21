package org.ddouglascarr.controlers;

import org.ddouglascarr.models.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by daniel on 21/04/16.
 */
@RestController
public class Root
{

    @RequestMapping(
            value="/",
            method= RequestMethod.GET
    )
    public ResponseEntity<String> getMember()
    {
        return new ResponseEntity<String>("Hello World", HttpStatus.OK);
    }

}
