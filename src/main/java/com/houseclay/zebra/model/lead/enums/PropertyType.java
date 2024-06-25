package com.houseclay.zebra.model.lead.enums;

public enum PropertyType{
    GATED_APARTMENT("GATED_APARTMENT"),
    GATED_SOCIETY("GATED_SOCIETY"),
    VILLA("VILLA"),
    STANDALONE_BUILDING("STANDALONE_BUILDING"),
    PLOT("PLOT");

    public final String label;

    private PropertyType(String label) {
        this.label = label;
    }
}
