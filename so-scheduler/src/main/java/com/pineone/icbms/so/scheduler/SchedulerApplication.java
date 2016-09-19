package com.pineone.icbms.so.scheduler;

import com.pineone.icbms.so.profile.ProfileApplication;
import com.pineone.icbms.so.util.UtilApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by melvin on 2016. 9. 5..
 */
@Configuration
@ComponentScan("com.pineone.icbms.so")
@Import({UtilApplication.class, ProfileApplication.class})
@EnableAutoConfiguration
public class SchedulerApplication {

    public static void main(String[] args )
    {
        SpringApplication application = new SpringApplication(SchedulerApplication.class);
        application.run(args);
    }
}
