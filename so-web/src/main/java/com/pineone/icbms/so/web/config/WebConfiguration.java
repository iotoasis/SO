package com.pineone.icbms.so.web.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.TimeZone;

/**
 * Created by existmaster on 2016. 8. 9..
 */

@EnableAutoConfiguration
@ComponentScan("com.pineone.icbms.so")
public class WebConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(WebConfiguration.class, args);
    }
    
}
