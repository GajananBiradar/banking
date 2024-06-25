package com.houseclay.zebra.model.lead.enums;

public enum LeadStatus {
    NEW("NEW"),
    IN_PROGRESS("INPROGRESS"),
    VISIT_SCHEDULED("VISITSCHEDULED"), // for property owners,sellers
    APPOINTMENT_SCHEDULED("APPOINTMENTSCHEDULED"), //for buyers, tenants
    DISCARDED("DISCARDED"), // lead not converted, dump leads
    CONVERTED("CONVERTED"), // tenant, buyers converted
    ONBOARDED("ONBOARDED"); // owners, sellers property onbaorded

    public final String label;

    private LeadStatus(String label) {
        this.label = label;
    }

}
