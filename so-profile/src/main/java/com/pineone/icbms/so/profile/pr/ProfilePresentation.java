package com.pineone.icbms.so.profile.pr;

import com.pineone.icbms.so.contextinformation.entity.ContextInformation;
import com.pineone.icbms.so.contextinformation.pr.ContextInformationPresentation;
import com.pineone.icbms.so.contextinformation.temp.device.ConceptService;
import com.pineone.icbms.so.contextinformation.temp.device.DeviceObject;
import com.pineone.icbms.so.contextinformation.temp.device.TempConceptService;
import com.pineone.icbms.so.contextinformation.temp.device.VirtualObject;
import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.contextmodel.pr.ContextModelPresentation;
import com.pineone.icbms.so.contextmodel.pr.ContextModelTransFormObject;
import com.pineone.icbms.so.contextmodel.ref.ContextType;
import com.pineone.icbms.so.domain.proxy.DomainProxy;
import com.pineone.icbms.so.profile.entity.Profile;
import com.pineone.icbms.so.profile.logic.ProfileLogic;
import com.pineone.icbms.so.profile.logic.ProfileLogicImpl;
import com.pineone.icbms.so.profile.ref.DataValidation;
import com.pineone.icbms.so.profile.ref.ResponseMessage;
import com.pineone.icbms.so.util.exception.DataLossException;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public static final Logger logger = LoggerFactory.getLogger(ProfilePresentation.class);

    @Autowired
            ProfileLogic profileLogic;

    @Autowired
    ContextInformationPresentation contextInformationPresentation;

    @Autowired
    ContextModelPresentation contextModelPresentation;

    @Autowired
    ProfilePresentation profilePresentation;

//    ProfileLogic profileLogic = ProfileLogicImpl.newProfileLogic();

    //NOTE: 미리 등록되어 있는 ServiceModel 이름 조회
    @RequestMapping(value = "/servicemodel",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> retrieveServiceModelNameList(){
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<String> serviceModelNameList = profileLogic.retrieveServiceModelNameList();
        return serviceModelNameList;
    }

    //NOTE: 미리 등록되어 있는 contextModel 이름 조회
    @RequestMapping(value = "/contextmodel",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> retrieveContextModelNameList(){
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<String> contextModelNameList = profileLogic.retrieveContextModelNameList();
        return contextModelNameList;
    }

    //NOTE: 미리 등록되어 있는 Bizcontext 이름 조회
    @RequestMapping(value = "/bizcontext",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> retrieveBizContextNameList(){
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<String> BizContextNameList = profileLogic.retrieveBizContextNameList();
        return BizContextNameList;
    }

    //NOTE: Profile 입력 정보 작성 후 등록 -> 등록 결과 리턴
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage registerProfileController(@RequestBody Profile profile){
        //
        logger.info(LogPrint.inputInfoLogPrint() + ", ProfileId = " + profile.getId());
        logger.debug("Profile = " + profile.toString());

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

    //NOTE: Profile NameList 조회
    @RequestMapping(value = "/name",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> retrieveProfileNameList(){
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<String> profileNameList = profileLogic.retrieveProfileNameList();
        return profileNameList;
    }

    //NOTE: DB 에서 profile 상세 조회
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Profile retrieveProfileDetailController(@PathVariable("id")String profileId){
        //
        logger.info(LogPrint.inputInfoLogPrint() + ", ProfileId = " + profileId);
        logger.debug("ProfileId = " + profileId);
        Profile profile = profileLogic.retrieveProfileDetail(profileId);
        logger.debug("Profile = " + profile.toString());
        return profile;
    }

    //NOTE: ContextModelName 으로 프로파일 조회
    public Profile retrieveProfileConnectDetailController(String contextModelName){
        //
        logger.info(LogPrint.inputInfoLogPrint() + ", contextModelId = " + contextModelName);
        Profile profile = profileLogic.retrieveProfileDetail(contextModelName);
        logger.debug("Profile = " + profile.toString());
        return profile;
    }

    //NOTE: Profile IdList 조회
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> retrieveProfileIdList(){
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<String> profileIdList = profileLogic.retrieveProfileIdList();
        return profileIdList;
    }

    //NOTE: 테스트데이터 셋팅
    @RequestMapping(value = "/set", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void retrieveProfileDetailController(){
        //
        logger.info(LogPrint.inputInfoLogPrint());

        String name = "EmergencyTempCon";
        int minValue = 60;
        int maxValue = 100;
        String id = "CI-EMERGENCY-TEMP";
        DeviceObject deviceObject = VirtualObject.TemperatureSensor001; // 저작시 온도 센서 선택
        ConceptService conceptService = TempConceptService.temperature_measure_service; // 온도센서의 온도 측정 기능 선택

        ContextInformation contextInformation = new ContextInformation();
        contextInformation.setId(id);
        contextInformation.setName(name);
        contextInformation.setMinValue(minValue);
        contextInformation.setMaxValue(maxValue);
        contextInformation.setDeviceObjectName(deviceObject.toString());
        contextInformation.setConceptServiceName(conceptService.toString());

        // NOTE: DB에 저장
        com.pineone.icbms.so.contextinformation.ref.ResponseMessage responseMessage = contextInformationPresentation.registerContextInformationController(contextInformation);
        System.out.println(responseMessage.getMessage());

        ContextInformation contextInformation1 = new ContextInformation();
        contextInformation1.setId("CI-NOBODY");
        contextInformation1.setName("사람없음");
        contextInformation1.setMinValue(0);
        contextInformation1.setMaxValue(0);
        contextInformation1.setDeviceObjectName("재실센서");
        contextInformation1.setConceptServiceName("재실측정");

        com.pineone.icbms.so.contextinformation.ref.ResponseMessage responseMessage1 = contextInformationPresentation.registerContextInformationController(contextInformation1);
        System.out.println(responseMessage1.getMessage());

        List<String> contextInformationIdList = contextInformationPresentation.retrieveContextInformationIdList();
        for(String id1 : contextInformationIdList){
            System.out.println(id);
        }

        ContextModel contextModel = new ContextModel();
        contextModel.setName("공기이상");
        contextModel.setId("CM-COMFORT-AIR");
        contextModel.setContextType(ContextType.EmergencyType.toString());
        contextModel.setContextInformationIdList(contextInformationIdList);
        List<String> chooseDomainList = new ArrayList<>();
        chooseDomainList.add("DO-CLASSROOM");
        contextModel.setDomainIdList(chooseDomainList);

        ContextModel contextModel1 = new ContextModel();
        contextModel1.setName("TEST");
        contextModel1.setId("CM-ANNOUNCEMENT-ON");
        contextModel1.setContextType(ContextType.EmergencyType.toString());
        contextModel1.setContextInformationIdList(contextInformationIdList);
        contextModel1.setOccTime("20160825T101010");
        List<String> chooseDomainList1 = new ArrayList<>();
        chooseDomainList1.add("DO-CLASSROOM");
        contextModel1.setDomainIdList(chooseDomainList);

        com.pineone.icbms.so.contextmodel.ref.ResponseMessage responseMessage2 = contextModelPresentation.registerGeneralContextController(contextModel);
        com.pineone.icbms.so.contextmodel.ref.ResponseMessage responseMessage3 = contextModelPresentation.registerGeneralContextController(contextModel1);

        System.out.println(responseMessage2.getMessage());
        System.out.println(responseMessage3.getMessage());

        Profile profile = new Profile();
        profile.setId("PR-ANNOUNCEMENT");
        profile.setName("발표도우미");

        List<String> contextModelIdList = contextModelPresentation.retrieveContextModelIdList();
        for(String contextModelId : contextModelIdList){
            System.out.println(contextModelId);
        }


        profile.setContextModelId("CM-ANNOUNCEMENT-ON");
        profile.setServiceModelId("CLASSROOM-PRESENTATIONMODE-SERVICE");
        profile.setPeriod(0);

        ResponseMessage responseMessage4 = profilePresentation.registerProfileController(profile);
        System.out.println(responseMessage4.getMessage());

        Profile profile1 = new Profile();
        profile1.setId("PR-IDEALAIR");
        profile1.setName("공기정화");

        profile1.setContextModelId("CM-COMFORT-AIR");
        profile1.setServiceModelId("CLASSROOM-AIRIDEAL-SERVICE");
        profile1.setPeriod(0);

        ResponseMessage responseMessage5 = profilePresentation.registerProfileController(profile1);
        System.out.println(responseMessage5.getMessage());

    }


    //NOTE: 스케줄러에서 체크가 필요한 프로파일을 알려줌
    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage executeScheduleProfile(@RequestBody ProfileTransFormData profileTransFormData){

        logger.info(LogPrint.inputInfoLogPrint() + ", ProfileId = " + profileTransFormData.getId());
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();

//        System.out.println("Profile ID = " + profileTransFormData.getId());
//        System.out.println();
        Profile profile = dataObjectToProfile(profileTransFormData);
        logger.debug("Profile = " + profileTransFormData.toString());
        String resultMessage = profileLogic.executeScheduleProfile(profile.getId());
        responseMessage.setMessage(resultMessage);
        return responseMessage;
    }

    private Profile dataObjectToProfile(ProfileTransFormData profileTransFormData){
        if(profileTransFormData == null) return null;
        return new Profile(profileTransFormData.getId());
    }

    //NOTE: 미리 등록되어 있는 Profile 조회
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Profile> retrieveProfileList(){
        logger.info(LogPrint.inputInfoLogPrint());
        List<Profile> profileList = profileLogic.retrieveProfileList();
        return profileList;
    }
}
