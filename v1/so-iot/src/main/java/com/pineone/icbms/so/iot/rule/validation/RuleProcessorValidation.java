package com.pineone.icbms.so.iot.rule.validation;

import com.pineone.icbms.so.resources.model.repo.context.DefaultContextModel;
import com.pineone.icbms.so.resources.model.repo.profile.DefaultProfileModel;
import com.pineone.icbms.so.resources.model.repo.service.DefaultServiceModel;

import java.util.List;

/**
 * RuleProcessor Validator<BR/>
 * Created by Melvin on 2015. 12. 24..
 *
 */
public class RuleProcessorValidation extends Exception{

    /**
     * Check Existence of ProfileList<BR/>
     * @param profiles
     * @throws Exception
     */
    public void inspectProfiles(List<DefaultProfileModel> profiles) throws Exception{
        if(profiles == null || profiles.size() == 0 )
            throw new NotExistProfileListException();
    }

    /**
     * check Existence of ContextId<BR/>
     * @param contextArrayList
     * @throws Exception
     */
    public void inspectContextId(List<DefaultContextModel> contextArrayList) throws Exception{
        if(contextArrayList == null || contextArrayList.size() == 0)
            throw new NotExistContextIdException();
    }

    /**
     * Check Existence of ServiceModelId<BR/>
     * @param serviceList
     * @throws Exception
     */
    public void inspectServiceId(List<DefaultServiceModel> serviceList) throws Exception{
        if(serviceList == null || serviceList.size() == 0)
            throw new NotExistServiceException();
    }

}
