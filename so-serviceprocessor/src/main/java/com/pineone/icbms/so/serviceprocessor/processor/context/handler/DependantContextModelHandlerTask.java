package com.pineone.icbms.so.serviceprocessor.processor.context.handler;

import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pineone.icbms.so.util.Settings2;
import com.pineone.icbms.so.util.conversion.DataConversion;
import com.pineone.icbms.so.util.conversion.ProfileTransFormData;
import com.pineone.icbms.so.util.http.ClientServiceNoTimeout;

public class DependantContextModelHandlerTask extends TimerTask {

    protected Logger log = LoggerFactory.getLogger(getClass());

    List<String> profileDeps;
    
    @Override
	public void run() {
    	Integer profileSize = profileDeps.size();
    	
	    log.debug("callDependantProfile : dependant profiles({}) = {}", profileSize, profileDeps.toString());
	    ClientServiceNoTimeout clientService = new ClientServiceNoTimeout();

	    for (String childId : profileDeps) {
		    ProfileTransFormData profileTransFormData = new ProfileTransFormData(childId);
		    String sendData = DataConversion.objectToString(profileTransFormData);
		    String profileControllerUrl = "http://localhost:" + Settings2.getServerPort() + Settings2.getContextPath() + "/service/profile/schedule";
		    log.debug(" profileControllerUrl = " + profileControllerUrl);
		    String result = clientService.requestPostServiceReceiveString2(profileControllerUrl, sendData);
		    log.debug("---result=" + result);
	    }
	}

	public DependantContextModelHandlerTask(List<String> profileDeps) {
		this.profileDeps = profileDeps;
	}
	
}
