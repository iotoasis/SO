package com.pineone.icbms.so.contextinformation;

import com.pineone.icbms.so.util.UtilApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by existmater on 2016. 8. 9..
 */

@SpringBootApplication
@Configuration
@ComponentScan("com.pineone.icbms.so")
@EnableAutoConfiguration
@Import(UtilApplication.class)
public class ContextInformationApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ContextInformationApplication.class, args);
    }
}
