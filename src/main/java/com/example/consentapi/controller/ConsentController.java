package com.example.consentapi.controller;

import com.example.consentapi.domain.ConsentRequested;
import com.example.consentapi.domain.InvalidConsentException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
public class ConsentController {


    // Takes precedence over the global error handler
    //@ExceptionHandler(InvalidConsentException.class)
    public ResponseEntity<String> handleInvalidConsentException(InvalidConsentException e) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Invalid request: " + e.getMessage());
    }


    @PostMapping(path = "/accessmanagement/api/v1/maskinporten/consent/lookup/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getConsentPost(@RequestBody ConsentRequested request) {

        String id = request.id();
        String from = request.from();
        String to = request.to();

        if ("cf8d5978-9ce9-4b4d-8622-dcb8ca1cc4b2".equals(id)) {
            throw new InvalidConsentException("You had a special id=%s and will always be doomed to fail".formatted(id));
        }

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> json = getJsonMockResponse(id, from, to);

        return new ResponseEntity<>(json, httpHeaders, HttpStatus.OK);

    }

    private String getJsonProblemDetails(String id) {
        return """
                {
                  "type": "https://tools.ietf.org/html/rfc7807",
                  "title": "An error occurred while processing your request.",
                  "code": "STD-00001",
                  "status": 400,
                  "detail": "You had a special id=%s and will always be doomed to fail.",
                }
                """.formatted(id);
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


    @GetMapping("/accessmanagement/api/v1/maskinporten/consent/lookup/")
    public Map<String, Object> getConsent() {
        return getJsonMockResponse("b55b0a8c-46db-4239-a417-a89daabfabba", "urn:altinn:person:identifier-no:01039012345", "urn:altinn:organization:identifier-no:984851006");

    }


    @PostMapping(path = "/accessmanagement/api/v1/maskinporten/consent/lookup2/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public ResponseEntity<Map<String, Object>> postConsent() {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> json = Map.of("me", "hello mr post 4");
        return new ResponseEntity<>(json, httpHeaders, HttpStatus.OK);
    }

    @PostMapping(path = "/accessmanagement/api/v1/maskinporten/consent/lookup3/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getConsentPost() {
        // test exception handling
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String id = "b55b0a8c-46db-4239-a417-a89daabfabba";
        String from = "urn:altinn:person:identifier-no:30886994385";
        String to = "urn:altinn:organization:identifier-no:991825827";
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> json = getJsonMockResponse(id, from, to);
        return new ResponseEntity<>(json, httpHeaders, HttpStatus.OK);
    }
}
