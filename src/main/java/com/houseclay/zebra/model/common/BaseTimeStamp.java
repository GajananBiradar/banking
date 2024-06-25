package com.houseclay.zebra.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Embeddable
public class BaseTimeStamp {

    @Column(nullable = true)
    private String created_by;

    @Column(nullable = true)
    private Date created_on;

    @Column(nullable = true)
    private String changed_by;

    @Column(nullable = true)
    private Date changed_on;

}
