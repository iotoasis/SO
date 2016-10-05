package com.pineone.icbms.so.creator;

import com.pineone.icbms.so.bizcontext.BizContextApplication;
import com.pineone.icbms.so.compositevo.CompositeVirtualObjectApplication;
import com.pineone.icbms.so.contextinformation.ContextInformationApplication;
import com.pineone.icbms.so.contextmodel.ContextModelApplication;
import com.pineone.icbms.so.device.DeviceApplication;
import com.pineone.icbms.so.domain.DomainApplication;
import com.pineone.icbms.so.profile.ProfileApplication;
import com.pineone.icbms.so.scheduler.SchedulerApplication;
import com.pineone.icbms.so.service.ServiceApplication;
import com.pineone.icbms.so.servicemodel.ServiceModelApplication;
import com.pineone.icbms.so.util.UtilApplication;
import com.pineone.icbms.so.virtualobject.VirtualObjectApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by melvin on 2016. 9. 28..
 */
@Configuration
@ComponentScan("com.pineone.icbms.so")
@Import({ContextInformationApplication.class, ContextModelApplication.class, DeviceApplication.class, ServiceApplication.class,
        UtilApplication.class, ServiceModelApplication.class, DomainApplication.class, BizContextApplication.class
        ,VirtualObjectApplication.class, CompositeVirtualObjectApplication.class, ProfileApplication.class, SchedulerApplication.class})
@EnableAutoConfiguration
public class CreatorApplication {
    public static void main( String[] args )
    {
        SpringApplication.run(CreatorApplication.class, args);
    }
}