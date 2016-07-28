package com.pineone.icbms.so.context.contextmodel.pr;

import com.pineone.icbms.so.context.contextmodel.ContextModel;
import com.pineone.icbms.so.context.contextmodel.domain.Domain;
import com.pineone.icbms.so.context.contextmodel.type.ContextType;
import com.pineone.icbms.so.context.general.GeneralContext;
import com.pineone.icbms.so.context.general.exception.DataLossException;
import com.pineone.icbms.so.context.util.address.AddressStore;
import com.pineone.icbms.so.context.util.check.DataValidation;
import com.pineone.icbms.so.context.util.response.ResponseMessageToUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melvin on 2016. 7. 22..
 * NOTE: ContextModel 관련 저작
 */
public class UserController_ContextModel {
    //
    ContextModel contextModel;

    //NOTE: ContextModel 생성 요청 -> GeneralContext 리스트 리턴
    @RequestMapping(value = AddressStore.REQUIRE_CONTEXTMODEL, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<GeneralContext> requestContextModelMakingController(){
        //
        contextModel = ContextModel.newContextModel();
        List<GeneralContext> generalContextList = contextModel.retrieveGeneralContextList();
        return generalContextList;
    }

    //NOTE: DomainList 조회
    @RequestMapping(value = AddressStore.RETRIEVE_DOMAIN, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Domain> retrieveDomainListController(){
        //
        contextModel = ContextModel.newContextModel();
        List<Domain> domainList = contextModel.retrieveDomainList();
        return domainList;
    }

    //NOTE : ContextType 조회
    @RequestMapping(value = AddressStore.REQUIRE_CONTEXTMODEL, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ContextType> retrieveContextTypeListController(){
        //
        contextModel = ContextModel.newContextModel();
        List<ContextType> contextTypeList = contextModel.retrieveContextTypeList();
        return contextTypeList;
    }

    //NOTE: ContextModel 입력 정보 작성 후 등록 -> 등록 결과 리턴
    @RequestMapping(value = AddressStore.REGISTER_CONTEXTMODEL, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessageToUser registerGeneralContextController(@RequestBody ContextModel contextModel){
        //
        DataValidation dataValidation = DataValidation.newGeneralContextValidation();
        ResponseMessageToUser responseMessage = ResponseMessageToUser.newResponseMessage();
        try {
            dataValidation.inspectContextModel(contextModel);
        } catch (DataLossException e) {
            responseMessage.setExceptionMessage(e.getMessage());
            return responseMessage;
        }
        contextModel.registerContextModel(contextModel);
        responseMessage.setMessage(responseMessage.contextModelResultMessage(contextModel));
        return responseMessage;
    }

    //NOTE: ContextModel 상세 정보 조회
    @RequestMapping(value = AddressStore.RETRIEVE_CONTEXTMODEL, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ContextModel> retrieveContextModelListController(){
        //
        contextModel = ContextModel.newContextModel();
        List<ContextModel> contextModelList = contextModel.retrieveContextModelList();
        return contextModelList;
    }

    //NOTE: ContextModel List 조회
    @RequestMapping(value = AddressStore.RETRIEVE_CONTEXTMODEL_DETAIL, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ContextModel retrieveContextModelDetailController(@PathVariable("contextmodelname")String contextModelName){
        //
        contextModel = ContextModel.newContextModel().retrieveContextModel(contextModelName);
        return contextModel;
    }
}
