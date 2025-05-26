package com.example.consentapi.domain;

public record ErrorResponse(String messsage, String description){

    public ErrorResponse() {
        this(null, null);
    }


}
