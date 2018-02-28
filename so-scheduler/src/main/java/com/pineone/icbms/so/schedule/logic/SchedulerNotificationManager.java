package com.pineone.icbms.so.schedule.logic;

import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import com.pineone.icbms.so.util.Settings2;
import com.pineone.icbms.so.util.conversion.DataConversion;
import com.pineone.icbms.so.util.conversion.ProfileTransFormData;
import com.pineone.icbms.so.util.http.ClientServiceNoTimeout;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by melvin on 2017. 4. 27..
 */

// Profile 에 등록된 주기를 참조해서 Job 실행  > ProfileInjector 에 전송
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SchedulerNotificationManager implements Job {
    public static final boolean CHECK_ONLY = true;
    public static final boolean CHECK_AND_RUN = false;

	protected Logger log = LoggerFactory.getLogger(getClass());

    protected int count=0;
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //
        long time = System.currentTimeMillis();
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String timeStr = timeFormat.format(new Date(time));

        String profileId = context.getJobDetail().getKey().getName();
        String groupName = context.getJobDetail().getKey().getGroup();

        log.debug("### Checking Time by schedule({}) :{}",profileId, timeStr);
        
        SimpleTrigger  st = (SimpleTrigger ) context.getTrigger();
        int checkRate = (int)st.getRepeatInterval()/1000;

        //ServiceProcessor 의 ProfileInjector 에 주기에 따라 profile Id 전송
//        ProfileInjector profileInJector = new ProfileIntjector();
//        profileInJector.sendProfile(profileId);

        //JobDataMap dataMap = context.getMergedJobDataMap();
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        //JobDataMap dataMap = context.getTrigger().getJobDataMap();

        ProfileForDB profileForDB = (ProfileForDB)dataMap.get("profile");
        
        //log.debug("profileForDB.getPeriod()={}", profileForDB.getPeriod());

        int period = profileForDB.getPeriod();
        //int checkRate 	= dataMap.getInt("checkRate");
     	boolean happened 	= dataMap.getBoolean("happened"); //초기값=미발생
     	long lastTimeExecutedCm = dataMap.getLong("lastTimeExecutedCm"); //마지막 CM 발생 일시
        
		//context.getJobDetail().getJobDataMap().put("lastTimeExecutedCm", lastTimeExecutedCm);
     	
        long currentTime = (new Date().getTime())/1000; //to sec
        boolean result;

        log.debug("## past={}, {} - {}", (currentTime - lastTimeExecutedCm), currentTime, lastTimeExecutedCm);
        
		if (happened) { //이미 CM을 처리했으면
			if (lastTimeExecutedCm==0 ||  (lastTimeExecutedCm + period) <= currentTime){ //시간이 경과 했으면
				result = checkCm(profileId, CHECK_AND_RUN); //CM조사해서 발생했으면 실행후 발생(실행) 여부 리턴
				lastTimeExecutedCm = currentTime; //마지막 실행시간 저장
				dataMap.put("lastTimeExecutedCm", lastTimeExecutedCm);
			} else {
				result = checkCm(profileId, CHECK_ONLY); //CM조사해서발생여부만 리턴
			}
			if (result == false) {//발생하지 않으면
				happened = false; // 실행 여부 초기화
				dataMap.put("happened", happened);
			}
			
		} else { //CM이 실행하지 않았으면
			result = checkCm(profileId, CHECK_AND_RUN); //CM실행후 발생(실행) 여부 리턴
			if (result == true) {//처리되었으면
				happened = true; // 실행했음
				lastTimeExecutedCm = currentTime;

				dataMap.put("happened", happened);
				dataMap.put("lastTimeExecutedCm", lastTimeExecutedCm);
			}
		}

		dataMap.put("lastTimeExecutedCm", lastTimeExecutedCm);
     	dataMap.put("profile", profileForDB);

     	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
     	String ctime = sdf.format(new Date());
     	log.debug("{} - result={}, happened={}, lastTimeExecutedCm={}\n", ctime, result, happened, lastTimeExecutedCm);
     	
    }
    
	private boolean checkCm(String profileId, boolean checkOnly) {
		ClientServiceNoTimeout clientService = new ClientServiceNoTimeout();
        ProfileTransFormData profileTransFormData = new ProfileTransFormData(profileId);
        //profileTransFormData.setPeriod(period);
        profileTransFormData.setCheckOnly(checkOnly);;
        String sendData = DataConversion.objectToString(profileTransFormData);

        //clientService.requestPostService("http://localhost:8080/so/service/profile/schedule", sendData);
        String profileControllerUrl = "http://localhost:" + Settings2.getServerPort() + Settings2.getContextPath() + "/service/profile/schedule";
        log.debug(" profileControllerUrl = " + profileControllerUrl);
        //clientService.requestPostService(profileControllerUrl, sendData);
        String result = clientService.requestPostServiceReceiveString2(profileControllerUrl, sendData);
        //result == DefaultProfile() == IGenericProfile
        log.debug("--- profileId={}, result={}", profileId, result);
        return (result!=null && result.equals("1"));
	}

    

}
