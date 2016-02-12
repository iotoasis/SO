package com.pineone.icbms.so.iot.rule;

import com.pineone.icbms.so.iot.provider.ProfileProvider;
import com.pineone.icbms.so.iot.resources.occurrence.DefaultOccurrence;
import com.pineone.icbms.so.iot.resources.service.ServiceSketch;
import com.pineone.icbms.so.iot.rule.validation.RuleProcessorValidation;
import com.pineone.icbms.so.resources.model.repo.profile.DefaultProfileModel;
import com.pineone.icbms.so.resources.model.repo.service.DefaultServiceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * RuleProcessor 의 역할 1. Occ 객체를 수신받아 객체에 들어있는 CM 을 키로 DB연결. 해당하는 Profile List를 수신 <BR/>
 * 2. Profile 객체에서 해당 CM 으로 ServiceModel 정보를 뽑아 실제 ServiceModel을 수신 <BR/>
 * 3. 해당과정에 대한 validation <BR/>
 * 4. 필요한ServiceModelId을 추출하여 Service Creator 에게 전송(논의 필요 Queue 사용?) <BR/>
 * Created by Melvin on 2015. 12. 23..
 */
public class RuleProcessor
{

    /**
     * 디비에 접속하여 occurrence로 profileList를 조회하고, Profilist의 profile들을 뒤져서 해당하는 Profile의 ServiceModelId를 선별하여서
     * 반환.<BR/>
     * @param occurrence
     * @return
     */
	public ServiceSketch executeRule(DefaultOccurrence occurrence)
	{

        RuleProcessorValidation ruleProcessorValidation = new RuleProcessorValidation();
        ProfileProvider profileProvider = new ProfileProvider();
        List<DefaultProfileModel> profileModelArrayList ;

        ArrayList<String> serviceModelIdList = new ArrayList<>();

		String contextId = occurrence.getContextId();
        ServiceSketch serviceSketch = new ServiceSketch();


        /** Provider를 이용하여 디비 접속. 데이터 조회 <BR/> */
        try {

            /**
             * DataBase에 접속하여 occurrence 의 ContextID를 이용하여 해당하는 ContextID를 가지고 있는 DefaultProfileModel 객체들을
             * ArrayList로 받아온다.<BR/>
             */
            profileModelArrayList = profileProvider.getDataListByContextID(contextId, DefaultProfileModel.class);

            ruleProcessorValidation.inspectProfiles(profileModelArrayList);

            /**
             * ProfileModel 들을 반복하여 추출하여 , ServiceSketch 객체에 필요한 값을 넣어서 리스트로 만들어 리턴해준다.<BR/>
             */

            for (DefaultProfileModel profileModel : profileModelArrayList) {

                serviceSketch.setDomainList(occurrence.getDomainList());
                serviceSketch.setDomainType(profileModel.getDomain());


                ruleProcessorValidation
                        .inspectServiceId(profileModel.getServiceModelList());

                for (DefaultServiceModel serviceModelUnit : profileModel.getServiceModelList()) {

                    serviceModelIdList.add(serviceModelUnit.getId());

                }
                serviceSketch.setServiceModelIdList(serviceModelIdList);

            }
        }

        /** validation에 걸릴경우 (profile이나 service가 null일 경우) <BR/> */
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

//        for(DefaultServiceModel serviceModelUnit : serviceModelList){
//            serviceModelIdList.add(serviceModelUnit.getId());
//        }

        return serviceSketch;

	}
}
