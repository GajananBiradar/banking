package com.houseclay.zebra.dto;

import com.houseclay.zebra.model.rental.Owner;
import com.houseclay.zebra.model.common.PropertySpecs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PropertyRentDTO {

    private UUID property_id;
    private String name;  //name of the society or apartment or the building
    private String title;
    private Owner owner;
    private boolean isManaged;
    PropertySpecs propertySpecs;
    private List<ImageResponse> imageMap;
    private boolean active_status;
    private String inactive_reason;
    private Date posted_on;
    private Date available_from;
    private double property_rent;
    private double property_maintenance;
    private String preferred_tenant_type;
    private String created_by;
    private Date created_on;
    private String changed_by;
    private Date changed_on;

}
