package com.rupicard.controller;

import com.rupicard.service.RupiCardService;
import com.rupicard.view.AddDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
public class RupiCardController {

    @Autowired
    private RupiCardService rupiCardService;

    @RequestMapping(value = "/api/addData", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> addData(@RequestBody AddDataRequest addDataRequest) throws GeneralSecurityException, IOException {

        rupiCardService.addData(addDataRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
