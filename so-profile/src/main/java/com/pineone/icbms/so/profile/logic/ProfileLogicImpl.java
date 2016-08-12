package com.pineone.icbms.so.profile.logic;

import com.pineone.icbms.so.bizcontext.pr.BizContextPresentation;
import com.pineone.icbms.so.contextmodel.pr.ContextModelPresentation;
import com.pineone.icbms.so.contextmodel.ref.ContextType;
import com.pineone.icbms.so.profile.entity.Profile;
import com.pineone.icbms.so.profile.ref.ResponseMessage;
import com.pineone.icbms.so.profile.store.ProfileMapStore;
import com.pineone.icbms.so.profile.store.ProfileStore;
import com.pineone.icbms.so.servicemodel.pr.ServiceModelPresentation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 11..
 * NOTE: Profile 생성 및 실행 로직 포함
 */
public class ProfileLogicImpl implements ProfileLogic{
    //

    ServiceModelPresentation serviceModelPresentation = new ServiceModelPresentation();
    ContextModelPresentation contextModelPresentation = new ContextModelPresentation();
    BizContextPresentation bizContextPresentation = new BizContextPresentation();

    public static ProfileLogicImpl newProfileLogic() {
        return new ProfileLogicImpl();
    }

    //NOTE : ContextModel Component 의 프레젠테이션을 이용하여 SO에 등록된 ContextModel 들의 이름들을 받아옴
    @Override
    public List<String> retrieveContextModelNameList() {
        //
        List<String> contextModelNameList = contextModelPresentation.retrieveContextModelList();
        return contextModelNameList;
    }

    //NOTE : ServiceModel Component 의 프레젠테이션을 이용하여 SO에 등록된 ServiceModel 들의 이름들을 받아옴
    @Override
    public List<String> retrieveServiceModelNameList() {
        //
        List<String> serviceModelNameList = serviceModelPresentation.retrieveServiceModelList();
        return serviceModelNameList;
    }

    //NOTE : BizComponent 의 프레젠테이션을 이용하여 SO에 등록된 BizContext 의 이름들을 받아옴
    @Override
    public List<String> retrieveBizContextNameList() {
        //
        List<String> bizContextTypeList = bizContextPresentation.retrieveBizContextList();
        return bizContextTypeList;
    }

    //NOTE : Profile 등록 정보를 수신받고 SO DB에 저장하고 보여줘야할 내용(ResponseMessage)을 리턴
    @Override
    public String registerProfile(Profile profile) {
        //
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        ProfileStore profileStore = ProfileMapStore.getInstance();

        //NOTE: ScheduleType 인데 스케쥴이 설정 안되있는 경우
        String contextModelType = contextModelPresentation.retrieveContextModelType(profile.getContextModelName());
        if(contextModelType.equals(ContextType.ScheduleType.toString())){
            if(profile.getPeriod() == 0){
                String profileResultMessage = "Input ScheduleTime";
                return profileResultMessage;
            }
            else{
                // TODO : 스케쥴러에 등록 - (Profile 이름, 스케쥴)
            }
        }
        profileStore.createProfile(profile);
        String profileResultMessage = responseMessage.profileResultMessage(profile);
        return profileResultMessage;
    }

    //NOTE: 저장된 Profile List 조회
    @Override
    public List<String> retrieveProfileNameList() {
        //
        ProfileStore profileStore = ProfileMapStore.getInstance();
        List<String> profileNameList = new ArrayList<>();
        List<Profile> profileList = profileStore.retrieveProfileList();

        for(Profile profile : profileList){
            profileNameList.add(profile.getName());
        }
        return profileNameList;
    }

    //NOTE: DB에 저장된 Profile 상세 내역 조회
    @Override
    public Profile retrieveProfileDetail(String profileName) {
        //
        ProfileStore profileStore = ProfileMapStore.getInstance();
        Profile profile = profileStore.retrieveProfileDetail(profileName);
        return profile;
    }
}
