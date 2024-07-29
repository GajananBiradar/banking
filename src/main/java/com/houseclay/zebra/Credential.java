package com.houseclay.zebra;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("zebra")
public class Credential {

    private String username;
    private String password;
}
