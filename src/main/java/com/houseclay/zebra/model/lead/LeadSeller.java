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
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "leads_seller")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeadSeller {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID _seller_lead_id;

    @Embedded
    private Lead lead;

    private PropertyType propertyType;

    private String bhkType;

    private String apartmentName;

    private String locality;

    private String googleMapLocationURL;

    private LeadStatus leadStatus;

    private Long expectedPrice;

    private boolean isRentalPossible;

    private String currentlyOccupiedBy; // None , Seller, Tenants
}
