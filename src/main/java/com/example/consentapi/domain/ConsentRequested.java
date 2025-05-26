package com.example.consentapi.domain;

public record ConsentRequested(String id, String from, String to) {

    public ConsentRequested() {
        this(null, null, null);
    }
}
