package com.houseclay.zebra.dto;

import com.houseclay.zebra.model.Role;
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
public class UserDTO {

    private UUID id;

    private String username;
    private ArrayList<Role> roles=new ArrayList<>();
    private String firstName;
    private String lastName;
    private String contactNumber;
    private boolean active;
    private boolean isEmailVerified;
    private boolean isPhoneVerified;
    private String notes;
    private BaseTimeStamp baseTimeStamp;

}
