package com.houseclay.zebra.model;

import com.houseclay.zebra.model.common.BaseTimeStamp;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "tbl_users")
@ToString
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID" , strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String username; // it should be email

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Role> roles = new ArrayList<>();

    private String firstName;

    private String lastName;

    private String contactNumber;

    private boolean active;

    private boolean isEmailVerified;

    private boolean isPhoneVerified;

    private String notes;

    @Embedded
    private BaseTimeStamp baseTimeStamp;
}
