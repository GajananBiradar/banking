package com.houseclay.zebra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OwnerLeadDTO {

    private String firstName;
    private String lastName;
    private String emailId;
    private String contactNumber;
    private String leadSource;
    private String notes;
    private String propertyType;
    private String apartmentName;
    private String locality;
    private String leadType;
    private String googleMapLocationUrl;
    private String expectedRent;
    private String expectedDeposit;
    private String furnishing;
    private Date availableFrom;
    private Boolean isEmailVerified;
    private Boolean isPhoneVerified;
    private String bhkType;
    private String preferredTenants;
    private Boolean isNonVegAllowed;
    private Boolean isPetAllowed;
    private Boolean clayManage;



}
