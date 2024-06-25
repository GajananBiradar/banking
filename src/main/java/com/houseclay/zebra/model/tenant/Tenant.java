package com.houseclay.zebra.model.tenant;

import com.houseclay.zebra.model.common.BaseTimeStamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "tbl_tenant")
public class Tenant {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID" , strategy = "org.hibernate.id.UUIDGenerator")
    private UUID tenant_id;

    @Embedded
    private TenantProfile tenantProfile;

    private boolean isActive; //By Default Make is Deactivated

    private boolean isEmailVerified;

    private boolean isPhoneVerified;

    @Column(nullable = true)
    private String notes;

    @Embedded
    private BaseTimeStamp baseTimeStamp;


}
