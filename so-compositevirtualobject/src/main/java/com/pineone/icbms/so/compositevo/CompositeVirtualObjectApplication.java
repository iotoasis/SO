package com.pineone.icbms.so.compositevo;

import com.pineone.icbms.so.util.UtilApplication;
import com.pineone.icbms.so.virtualobject.VirtualObjectApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Import({VirtualObjectApplication.class,UtilApplication.class})
public class CompositeVirtualObjectApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CompositeVirtualObjectApplication.class, args);
    }
}
