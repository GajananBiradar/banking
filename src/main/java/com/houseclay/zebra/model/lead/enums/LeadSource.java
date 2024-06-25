package com.houseclay.zebra.model.lead.enums;

public enum LeadSource {



    FACEBOOK("FACEBOOK"),
    INSTAGRAM("INSTAGRAM"),
    REFERENCE("REFERENCE"),
    OTHER("OTHER"),
    WEBSITE("WEBSITE");

    public final String label;

    private LeadSource(String label) {
        this.label = label;
    }
}
