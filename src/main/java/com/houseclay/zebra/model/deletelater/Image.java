package com.houseclay.zebra.model.deletelater;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * This class is a test image class. Refer to Images Entity , which is actually used
 */
@Entity
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class Image {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID" , strategy = "org.hibernate.id.UUIDGenerator")
    private UUID image_id;

    private String name;

    private String contentType;

    private Long size;

    @Lob
    private byte[] image_data;
}
