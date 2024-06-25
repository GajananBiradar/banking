package com.houseclay.zebra.model.lead.enums;

public enum LeadType {
    BUYER("BUYER"),
    OWNER("OWNER"),
    SELLER("SELLER"),
    TENANT("TENANT");

    public final String label;

    private LeadType(String label) {
        this.label = label;
    }
}
