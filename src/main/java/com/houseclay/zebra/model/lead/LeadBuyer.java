package com.houseclay.zebra.model.lead;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.houseclay.zebra.model.lead.enums.LeadStatus;
import com.houseclay.zebra.model.lead.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "leads_buyer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeadBuyer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID _buyer_lead_id;

    @Embedded private Lead lead;

    private Long minBudget;

    private Long maxBudget;

    private PropertyType propertyType;

    private LeadStatus leadStatus;

    private ArrayList<String> preferredLocations;

    private Date occupancyDate;

    private Boolean isDateFlexible;
}
