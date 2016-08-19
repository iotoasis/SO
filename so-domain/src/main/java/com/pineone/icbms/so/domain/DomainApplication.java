package com.pineone.icbms.so.domain;

import com.pineone.icbms.so.util.UtilApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by existmater on 2016. 8. 9..
 */
@Configuration
@ComponentScan("com.pineone.icbms.so.domain")
@Import(UtilApplication.class)
@EnableAutoConfiguration
public class DomainApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(DomainApplication.class, args);
    }
}
