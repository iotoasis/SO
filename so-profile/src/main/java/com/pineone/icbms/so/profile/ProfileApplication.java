package com.pineone.icbms.so.profile;

import com.pineone.icbms.so.bizcontext.BizContextApplication;
import com.pineone.icbms.so.contextmodel.ContextModelApplication;
import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.domain.DomainApplication;
import com.pineone.icbms.so.profile.entity.Profile;
import com.pineone.icbms.so.profile.logic.ProfileLogic;
import com.pineone.icbms.so.profile.pr.ProfilePresentation;
import com.pineone.icbms.so.profile.proxy.ProfileProxy;
import com.pineone.icbms.so.profile.store.ProfileStore;
import com.pineone.icbms.so.servicemodel.ServiceModelApplication;
import com.pineone.icbms.so.servicemodel.pr.ServiceModelPresentation;
import com.pineone.icbms.so.util.UtilApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 11..
 */
@Configuration
@ComponentScan
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
        profileLogic.extractQueueData();
    }
}
