package com.houseclay.zebra.dto;

import com.houseclay.zebra.model.lead.enums.LeadStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OwnerLeadListDTO {

    private UUID ownerID;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String emailId;
    private LeadStatus leadStatus;
    private String locality;
    private String bhkType;
    private String apartmentName;
    private String expectedRent;
    private String googleMapLocationURL;
    private String availableFrom;
    private Boolean clayManage;

}
