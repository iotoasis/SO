package com.pineone.icbms.so.profile.pr;

import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.profile.entity.Profile;
import com.pineone.icbms.so.profile.logic.ProfileLogic;
import com.pineone.icbms.so.profile.logic.ProfileLogicImpl;
import com.pineone.icbms.so.profile.ref.DataValidation;
import com.pineone.icbms.so.profile.ref.ResponseMessage;
import com.pineone.icbms.so.util.exception.DataLossException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * Created by melvin on 2016. 8. 11..
 * NOTE: Profile 이 제공하는 기능이나 저작을 위한 기능 정의
 */

@Controller
@RequestMapping(value = "/profile")
public class ProfilePresentation {
    //
    @Autowired
            ProfileLogic profileLogic;
//    ProfileLogic profileLogic = ProfileLogicImpl.newProfileLogic();

    //NOTE: 미리 등록되어 있는 ServiceModel 이름 조회
    @RequestMapping(value = "/servicemodel")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> retrieveServiceModelNameList(){
        //
        List<String> serviceModelNameList = profileLogic.retrieveServiceModelNameList();
        return serviceModelNameList;
    }

    //NOTE: 미리 등록되어 있는 contextModel 이름 조회
    @RequestMapping(value = "/contextmodel")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> retrieveContextModelNameList(){
        //
        List<String> contextModelNameList = profileLogic.retrieveContextModelNameList();
        return contextModelNameList;
    }

    //NOTE: 미리 등록되어 있는 Bizcontext 이름 조회
    @RequestMapping(value = "/bizcontext")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> retrieveBizContextNameList(){
        //
        List<String> BizContextNameList = profileLogic.retrieveBizContextNameList();
        return BizContextNameList;
    }

    //NOTE: Profile 입력 정보 작성 후 등록 -> 등록 결과 리턴
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage registerProfileController(@RequestBody Profile profile){
        //
        DataValidation dataValidation = DataValidation.newDataValidation();
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();

        try {
            dataValidation.inspectProfile(profile);
        } catch (DataLossException e) {
            responseMessage.setExceptionMessage(e.getMessage());
            return responseMessage;
        }
        String resultMessage = profileLogic.registerProfile(profile);
        responseMessage.setMessage(resultMessage);
        return responseMessage;
    }

    //NOTE: Profile List 조회
    @RequestMapping(value = "/profilelist")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> retrieveProfileNameList(){
        //
        List<String> profileNameList = profileLogic.retrieveProfileNameList();
        return profileNameList;
    }

    //NOTE: DB 에서 profile 상세 조회
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Profile retrieveProfileDetailController(@PathVariable("id")String profileId){
        //
        Profile profile = profileLogic.retrieveProfileDetail(profileId);
        return profile;
    }

    //NOTE: ContextModelName 으로 프로파일 조회
    public Profile retrieveProfileConnectDetailController(String contextModelName){
        //
        Profile profile = profileLogic.retrieveProfileDetail(contextModelName);
        return profile;
    }

    public List<String> retrieveProfileIdList(){
        //
        List<String> profileIdList = profileLogic.retrieveProfileIdList();
        return profileIdList;
    }
}
