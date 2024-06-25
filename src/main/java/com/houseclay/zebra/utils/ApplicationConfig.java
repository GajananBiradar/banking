package com.houseclay.zebra.utils;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@RequiredArgsConstructor
@Component
public class ApplicationConfig {

    @Value("${secretKey}")
    private String secretKey;

}
