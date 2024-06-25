package com.houseclay.zebra.model.lead;

import com.houseclay.zebra.model.common.BaseTimeStamp;
import com.houseclay.zebra.model.lead.enums.LeadSource;
import com.houseclay.zebra.model.lead.enums.LeadType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;


/**
 * Lead Base Class which needs to be embedded by all Leads
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Embeddable
public class Lead {


    private String firstName;

    private String lastName;

    private String emailId;

    private String contactNumber;

    private Boolean isEmailVerified = false;

    private Boolean isPhoneVerified = false;

    private String notes;

    private Boolean isLeadConverted = false;

    @Enumerated(EnumType.STRING)
    private LeadType leadType;

    private Boolean isLeadTrashed = false; //Unconverted Leads , Leads to be Blocked

    private String trashedReason;

    @Enumerated(EnumType.STRING)
    private LeadSource leadSource; //Facebook, Online Sites , Reference , Zebra Web App

    @Embedded
    private BaseTimeStamp baseTimeStamp;


}
