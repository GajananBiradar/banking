package com.houseclay.zebra.model.rental;

import com.houseclay.zebra.model.common.BaseTimeStamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Images Size should be designed to fit in houseclay portal
 *
 * 1. Default Size
 * 2. Full Size for Details Page
 * 3.
 */
@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class Images {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID" , strategy = "org.hibernate.id.UUIDGenerator")
    private UUID image_id;

    private String name;

    private String contentType;

    private Long size;

    @Lob
    private byte[] image_data;


    @Embedded
    private BaseTimeStamp baseTimeStamp;
}

