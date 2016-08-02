package com.pineone.icbms.so.context_model.pr;

import com.pineone.icbms.so.context_information.entity.ContextInformation;
import com.pineone.icbms.so.context_model.entity.ContextModel;
import com.pineone.icbms.so.context_model.entity.Domain;
import com.pineone.icbms.so.context_model.logic.ContextModelLogic;
import com.pineone.icbms.so.context_model.ref.ContextType;
import com.pineone.icbms.so.util.address.AddressStore;
import com.pineone.icbms.so.util.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by melvin on 2016. 8. 1..
 */
public class ContextModelPresentation {
    //
    ContextModel contextModel;

    //NOTE: ContextModel 생성 요청 -> ContextInformation 리스트 리턴
    @RequestMapping(value = AddressStore.REQUIRE_CONTEXTMODEL, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ContextInformation> requestContextModelMakingController(){
        //
        List<ContextInformation> contextInformationList = ContextModelLogic.newContextModelLogic().retrieveContextInformationList();
        return contextInformationList;
    }

    //NOTE: DomainList 조회
    @RequestMapping(value = AddressStore.RETRIEVE_DOMAIN, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Domain> retrieveDomainListController(){
        //
        List<Domain> domainList = ContextModelLogic.newContextModelLogic().retrieveDomainList();
        return domainList;
    }

    //NOTE : ContextType 조회
    @RequestMapping(value = AddressStore.REQUIRE_CONTEXTMODEL, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ContextType> retrieveContextTypeListController(){
        //
        List<ContextType> contextTypeList = ContextModelLogic.newContextModelLogic().retrieveContextTypeList();
        return contextTypeList;
    }

    //NOTE: ContextModel 입력 정보 작성 후 등록 -> 등록 결과 리턴
    @RequestMapping(value = AddressStore.REGISTER_CONTEXTMODEL, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage registerGeneralContextController(@RequestBody ContextModel contextModel){
        //
        ResponseMessage responseMessage = ContextModelLogic.newContextModelLogic().registerContextModel(contextModel);
        return responseMessage;
    }

    //NOTE: ContextModel List 퍼블리싱 -  Profile 생성시 사용
    public List<ContextModel> retrieveContextModelList(){
        //
        List<ContextModel> contextModelList = ContextModelLogic.newContextModelLogic().retrieveContextModelList();
        return contextModelList;
    }

    //NOTE: ContextModel 싱세 조회
    @RequestMapping(value = AddressStore.RETRIEVE_CONTEXTMODEL_DETAIL, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ContextModel retrieveContextModelDetailController(@PathVariable("contextmodelname")String contextModelName){
        //
        ContextModel contextModel = ContextModelLogic.newContextModelLogic().retrieveContextModelDetail(contextModelName);
        return contextModel;
    }

    //NOTE: ContextModel 상황 발생 여부 질의
    public List<Domain> isHappenContextModel(ContextModel contextModel){
        List<Domain> domainList = ContextModelLogic.newContextModelLogic().isHappenContextModel(contextModel);
        return domainList;
    }
}
