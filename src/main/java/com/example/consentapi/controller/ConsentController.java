package com.example.consentapi.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
public class ConsentController {


    @PostMapping(path = "/accessmanagement/api/v1/maskinporten/consent/lookup/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getConsentPost(@RequestBody ConsentRequested request) {

        String id = request.id();
        String from = request.from();
        String to = request.to();

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> json = getJsonMockResponse(id, from, to);

        return new ResponseEntity<>(json, httpHeaders, HttpStatus.OK);

    }


    private Map<String, Object> getJsonMockResponse(String id, String from, String to) {
        return Map.of(
                "id", UUID.fromString(id),
                "from", from,
                "to", to,
                "consented", "2024-06-01T00:00:00Z",
                "validTo", "2024-12-10T00:00:00Z",
                "consentRights", List.of(
                        Map.of(
                                "action", List.of("read", "write"),
                                "resource", List.of(
                                        Map.of(
                                                "id", "urn:altinn:resource",
                                                "value", "skd_inntektsapi"
                                        )
                                ),
                                "metadata", Map.of(
                                        "fraOgMed", "2017-06",
                                        "tilOgMed", "2017-08"
                                )
                        ),
                        Map.of(
                                "action", List.of("read", "write"),
                                "resource", List.of(
                                        Map.of(
                                                "id", "urn:altinn:resource",
                                                "value", "skd_skattegrunnlag"
                                        )
                                ),
                                "metadata", Map.of(
                                        "inntektsaar", "2016"
                                )
                        )
                )
        );
    }


    @GetMapping("/systemuser/byExternalId")
    public String getSystemUser() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "systemuser";
    }

    @GetMapping("/accessmanagement/api/v1/maskinporten/consent/lookup/")
    public Map<String, Object> getConsent() {
        return getJsonMockResponse("b55b0a8c-46db-4239-a417-a89daabfabba", "urn:altinn:person:identifier-no:01039012345", "urn:altinn:organization:identifier-no:984851006");

    }


    @PostMapping(path = "/badreq", produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public ResponseEntity<Map<String, Object>> badReq() {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @PostMapping(path = "/accessmanagement/api/v1/maskinporten/consent/lookup2/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public ResponseEntity<Map<String, Object>> postConsent() {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> json = Map.of("me", "hello mr post 4");
        ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(json, httpHeaders, HttpStatus.OK);
        return response;
    }

    @PostMapping(path = "/accessmanagement/api/v1/maskinporten/consent/lookup3/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getConsentPost() {
/*        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        String id = "b55b0a8c-46db-4239-a417-a89daabfabba";
        String from = "urn:altinn:person:identifier-no:30886994385";
        String to = "urn:altinn:organization:identifier-no:991825827";
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> json = getJsonMockResponse(id, from, to);
        ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(json, httpHeaders, HttpStatus.OK);
        return response;
    }
}
