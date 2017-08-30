package com.pineone.icbms.so.schedule.logic;

import com.pineone.icbms.so.util.conversion.DataConversion;
import com.pineone.icbms.so.util.conversion.ProfileTransFormData;
import com.pineone.icbms.so.util.http.ClientService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by melvin on 2017. 4. 27..
 */

// Profile 에 등록된 주기를 참조해서 Job 실행  > ProfileInjector 에 전송
public class SchedulerNotificationManager implements Job {


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //
        long time = System.currentTimeMillis();
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String timeStr = timeFormat.format(new Date(time));
        //System.out.println("Time " + timeStr);

        System.out.println(context.getJobDetail().getKey().getName());
        String profileId = context.getJobDetail().getKey().getName();

        //ServiceProcessor 의 ProfileInjector 에 주기에 따라 profile Id 전송
//        ProfileInjector profileInJector = new ProfileIntjector();
//        profileInJector.sendProfile(profileId);

        ClientService clientService = new ClientService();
        ProfileTransFormData profileTransFormData = new ProfileTransFormData(profileId);
        String sendData = DataConversion.objectToString(profileTransFormData);
        clientService.requestPostService("http://localhost:8080/so/service/profile/schedule", sendData);
        //System.out.println("######## Scheduler Test : ProfileId = " + profileTransFormData.getId());
    }
}
