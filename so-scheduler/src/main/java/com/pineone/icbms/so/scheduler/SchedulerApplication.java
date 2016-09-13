package com.pineone.icbms.so.scheduler;

import com.pineone.icbms.so.profile.ProfileApplication;
import com.pineone.icbms.so.scheduler.logic.SchedulerLogic;
import com.pineone.icbms.so.util.UtilApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;

/**
 * Created by melvin on 2016. 9. 5..
 */
@Configuration
@ComponentScan("com.pineone.icbms.so")
@Import({UtilApplication.class, ProfileApplication.class})
@EnableAutoConfiguration
public class SchedulerApplication implements CommandLineRunner{

    @Autowired
    SchedulerLogic schedulerLogic;

    public static void main( String[] args )
    {
        SpringApplication.run(SchedulerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Scheduler run???");
        schedulerLogic.runScheduler();
    }
}
