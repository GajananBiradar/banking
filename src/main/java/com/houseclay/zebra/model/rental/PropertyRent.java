package com.houseclay.zebra.model.rental;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.houseclay.zebra.model.common.BaseTimeStamp;
import com.houseclay.zebra.model.common.PropertySpecs;
import com.houseclay.zebra.model.tenant.Tenant;
import com.houseclay.zebra.model.tenant.TenantProfile;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "properties_for_rent")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyRent {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID" , strategy = "org.hibernate.id.UUIDGenerator")
    private UUID property_id;

    @Column(nullable = false)
    private String name;  //name of the society or apartment or the building

    @Column(nullable = false)
    private String title;

    @OneToOne(targetEntity = Owner.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_reference_id",referencedColumnName = "owner_id")
    private Owner owner;

    private boolean isManaged;

    @Embedded
    PropertySpecs propertySpecs;

    @OneToMany(targetEntity = Images.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "mapped_property",referencedColumnName = "property_id")
    private List<Images> imageMap;

    @Column(nullable = false)
    private boolean active_status;

    private String inactive_reason;

    @Column(nullable = false)
    private Date posted_on;

    @Column(nullable = false)
    private Date available_from;

    @Column(nullable = false)
    private double property_rent;

    @Column(nullable = false)
    private double property_maintenance;

    private String preferred_tenant_type;

    @OneToMany(targetEntity = Tenant.class,cascade = CascadeType.ALL)
    @JoinColumn(name="mapped_to_property",referencedColumnName = "property_id")
    private List<Tenant> tenantList;

    @Embedded
    private BaseTimeStamp baseTimeStamp;



}
