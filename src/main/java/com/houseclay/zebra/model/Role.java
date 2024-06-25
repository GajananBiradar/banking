package com.houseclay.zebra.model;


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
@Table(name = "tbl_roles")
public class Role {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    private String type;

    @Embedded
    private BaseTimeStamp baseTimeStamp;

}
