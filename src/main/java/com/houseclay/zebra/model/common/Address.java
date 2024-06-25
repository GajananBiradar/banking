package com.houseclay.zebra.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name="address")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID" , strategy = "org.hibernate.id.UUIDGenerator")
    private UUID addr_id;
    private String house_no;
    private String street_name;
    private String property_name; //flat name or apartment name or society name
    private Long pincode;
    private String city;
    private String state;



}
