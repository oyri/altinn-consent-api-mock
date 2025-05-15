package com.example.consentapi.controller;

public record ConsentRequested(String id, String from, String to) {

    public ConsentRequested() {
        this(null, null, null);
    }
}
