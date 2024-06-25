package com.houseclay.zebra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserListDTO {

    private String user_id;
    private String username;
    private String name; //combine First Name and Last Name
}
