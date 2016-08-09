package com.pineone.icbms.so.contextmodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by existmater on 2016. 8. 9..
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ContextModelApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ContextModelApplication.class, args);
    }
}
