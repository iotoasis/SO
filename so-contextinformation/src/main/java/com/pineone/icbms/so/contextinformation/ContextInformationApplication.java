package com.pineone.icbms.so.contextinformation;

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
public class ContextInformationApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ContextInformationApplication.class, args);
    }
}
