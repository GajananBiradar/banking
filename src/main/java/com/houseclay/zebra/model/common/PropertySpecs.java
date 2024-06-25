package com.houseclay.zebra.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PropertySpecs {


    @OneToOne(targetEntity = Address.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "addr_id")
    private Address full_address;

    @Column(nullable = false)
    private String type;     // apartment , society , standalone

    private double super_area;

    private double carpet_area;

    @Column(nullable = false)
    private int beds;

    @Column(nullable = false)
    private int bath;

    @Column(nullable = false)
    private int balcony;

    @Column(nullable = false)
    private String parking ; //Car and Bike , Only Car


    private String facing ;

    @Column(nullable = false)
    private String property_age ;

    @Column(nullable = false)
    private String furnishing;


    private String flooring;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String description;


    @OneToOne(targetEntity = Amenities.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "amenitiesMap",referencedColumnName = "amenities_id")
    private Amenities amenitiesMap;

    private String latitude;

    private String longitude;

    private String locationurl;

}
