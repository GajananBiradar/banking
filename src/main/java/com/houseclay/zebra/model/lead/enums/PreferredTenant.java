package com.houseclay.zebra.model.lead.enums;

public enum PreferredTenant {

    BACHELORS("BACHELORS"),
    BACHELOR_GIRLS("BACHELOR_GIRLS"),
    BACHELOR_BOYS("BACHELOR_BOYS"),
    FAMILY("FAMILY"),
    COMPANY("COMPANY"),
    ANY("ANY");

    private final String label;

    private PreferredTenant(String label) {
        this.label = label;
    }
}
