package com.pineone.icbms.so.scheduler.logic;

import com.pineone.icbms.so.profile.pr.ProfileTransFormData;
import com.pineone.icbms.so.scheduler.proxy.SchedulerInnerProxy;
import com.pineone.icbms.so.scheduler.proxy.SchedulerProxy;
import com.pineone.icbms.so.util.conversion.DataConversion;
import com.pineone.icbms.so.util.http.ClientService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by melvin on 2016. 9. 5..
 */
//NOTE : ScheduledProfile 의 주기로 스케줄 실행 > Profile 에 전송
public class ScheduleNotificationManager implements Job{

    @Autowired
    SchedulerProxy schedulerProxy;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

//        ApplicationContext ctx = new AnnotationConfigApplicationContext(SchedulerInnerProxy.class);
//        System.out.println("ctx = " + ctx);
//        SchedulerProxy schedulerProxy = ctx.getBean("executeScheduledProfile", SchedulerInnerProxy.class);
//        System.out.println(schedulerProxy.toString());

//        ApplicationContext ctx = (ApplicationContext)context.getJobDetail().getJobDataMap().get("applicationContext");

//        SchedulerProxy schedulerProxy = (SchedulerProxy)ctx.getBean("schedulerInnerProxy");
//        System.out.println(schedulerProxy);

//        SchedulerProxy schedulerProxy1 = new SchedulerInnerProxy();
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String str = dayTime.format(new Date(time));
        System.out.println("Time" + str);

        System.out.println(context.getJobDetail().getKey().getName());
        String profileId = context.getJobDetail().getKey().getName();

//        System.out.println(schedulerProxy + " ????? ");
//        System.out.println(schedulerProxy1 + "!!!!!!");

//        schedulerProxy.executeScheduledProfile(profileId);

        //
        ProfileTransFormData profileTransFormData = new ProfileTransFormData(profileId);

        String sendData = DataConversion.objectToString(profileTransFormData);
        ClientService clientService = new ClientService();
        clientService.requestPostService("http://localhost:10080/so/profile/schedule",sendData);

//        profilePresentation.executeScheduleProfile(profileTransFormData);
    }
}
