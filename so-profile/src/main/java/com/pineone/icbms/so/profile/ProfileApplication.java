package com.pineone.icbms.so.profile;

import com.pineone.icbms.so.bizcontext.BizContextApplication;
import com.pineone.icbms.so.contextmodel.ContextModelApplication;
import com.pineone.icbms.so.domain.DomainApplication;
import com.pineone.icbms.so.profile.logic.ProfileLogic;
import com.pineone.icbms.so.servicemodel.ServiceModelApplication;
import com.pineone.icbms.so.util.UtilApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

/**
 * Created by melvin on 2016. 8. 11..
 */
@Configuration
@ComponentScan("com.pineone.icbms.so")
@Import({UtilApplication.class, ServiceModelApplication.class, ContextModelApplication.class, DomainApplication.class, BizContextApplication.class})
@EnableAutoConfiguration
public class ProfileApplication implements CommandLineRunner
{
    @Autowired
    ProfileLogic profileLogic;

    public static void main( String[] args )
    {
        SpringApplication.run(ProfileApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("profileRun");
        profileLogic.extractContextModelQueueData();
    }
}
