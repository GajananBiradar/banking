package com.houseclay.zebra.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "amenities")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class Amenities {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID" , strategy = "org.hibernate.id.UUIDGenerator")
    private UUID amenities_id;
    private boolean lift;
    private boolean club_house;
    private boolean intercom;
    private boolean swimming_pool;
    private boolean children_play_area;
    private boolean fire_safety;
    private boolean security;
    private boolean park;
    private boolean rain_water_harvesting;
    private boolean sewage_treatment_plant;
    private boolean housekeeping;
    private boolean power_backup;



}
