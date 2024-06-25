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
@Table(name = "leads_tenants")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeadTenant {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID _tenant_lead_id;

    @Embedded
    private Lead lead;

    private Long minBudget;
    private Long maxBudget;

    //    private String propertyType;
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    private String leadStatus;
    //    private LeadStatus leadStatus;

    private String preferredLocations;

    private String assetConfigurations;

    private Date occupancyDate;

    private Boolean isDateFlexible;

    private String tenantType; // family, bachelors, company

    private Boolean isVegetarian;

    private Boolean isHavingPets;

    private Boolean isLookingForARoom;
}
