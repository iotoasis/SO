package com.pineone.icbms.so.service;

import com.pineone.icbms.so.domain.DomainApplication;
import com.pineone.icbms.so.util.UtilApplication;
import com.pineone.icbms.so.virtualobject.VirtualObjectApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by existmater on 2016. 8. 9..
 */
@Configuration
@ComponentScan
@Import({UtilApplication.class, DomainApplication.class, VirtualObjectApplication.class})
@EnableAutoConfiguration
public class ServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
