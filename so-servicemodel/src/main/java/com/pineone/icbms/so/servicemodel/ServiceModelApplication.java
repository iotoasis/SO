package com.pineone.icbms.so.servicemodel;

import com.pineone.icbms.so.domain.DomainApplication;
import com.pineone.icbms.so.service.ServiceApplication;
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
@ComponentScan
@Import({ UtilApplication.class, DomainApplication.class, ServiceApplication.class})
@EnableAutoConfiguration
public class ServiceModelApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceModelApplication.class, args);
    }
}
