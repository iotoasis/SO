package com.pineone.icbms.so.contextmodel;


import com.pineone.icbms.so.contextinformation.ContextInformationApplication;
import com.pineone.icbms.so.domain.DomainApplication;
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
@EnableAutoConfiguration
@Import({UtilApplication.class, DomainApplication.class, ContextInformationApplication.class})
public class ContextModelApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ContextModelApplication.class, args);
    }
}
