package com.houseclay.zebra.dto;


import com.houseclay.zebra.model.common.BaseTimeStamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EditUserDTO {

    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private boolean active;
    private boolean isEmailVerified;
    private boolean isPhoneVerified;
    private String notes;
    private BaseTimeStamp baseTimeStamp;
    private ArrayList<String> roles = new ArrayList<>();
}
