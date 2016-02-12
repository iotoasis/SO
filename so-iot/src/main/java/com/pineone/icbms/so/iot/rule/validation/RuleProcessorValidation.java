package com.pineone.icbms.so.iot.rule.validation;

import com.pineone.icbms.so.resources.model.repo.context.DefaultContextModel;
import com.pineone.icbms.so.resources.model.repo.profile.DefaultProfileModel;
import com.pineone.icbms.so.resources.model.repo.service.DefaultServiceModel;

import java.util.List;

/**
 * Created by Melvin on 2015. 12. 24..
 * 룰 프로세서를 수행하는데 있어서 필요한 정보의 존재 여부를 체크한다.
 */
public class RuleProcessorValidation extends Exception{

    /** Profile 존재 여부 체크
     *
     * @param profiles
     * @throws Exception
     */
    public void inspectProfiles(List<DefaultProfileModel> profiles) throws Exception{
        if(profiles == null || profiles.size() == 0 )
            throw new NotExistProfileListException();
    }

    public void inspectContextId(List<DefaultContextModel> contextArrayList) throws Exception{
        if(contextArrayList == null || contextArrayList.size() == 0)
            throw new NotExistContextIdException();
    }

    /** ServiceList 존재 여부 체크
     *
     * @param serviceList
     * @throws Exception
     */
    public void inspectServiceId(List<DefaultServiceModel> serviceList) throws Exception{
        if(serviceList == null || serviceList.size() == 0)
            throw new NotExistServiceException();
    }

}
