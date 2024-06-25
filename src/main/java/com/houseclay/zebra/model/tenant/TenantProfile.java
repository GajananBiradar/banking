package com.houseclay.zebra.model.tenant;

import com.houseclay.zebra.model.nationalid.NationalID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TenantProfile {

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true,nullable = false)
    private String emailId;

    @OneToOne(targetEntity = NationalID.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_national_id",referencedColumnName = "national_id")
    private NationalID nationalID;

    @Column(nullable = false,unique = true)
    private String contactNumber;

    private String permanentAddress;


}
