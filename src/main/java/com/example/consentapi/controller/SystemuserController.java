package com.example.consentapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class SystemuserController {

    @GetMapping("/systemuser/byExternalId")
    public String getSystemUser() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "systemuser";
    }


    @PostMapping(path = "/badreq", produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public ResponseEntity<Map<String, Object>> badReq() {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
