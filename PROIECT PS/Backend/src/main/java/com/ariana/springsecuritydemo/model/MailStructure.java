package com.ariana.springsecuritydemo.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class MailStructure {
    private String subject;
    private String message;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MailStructure(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    public MailStructure() {
    }
}
