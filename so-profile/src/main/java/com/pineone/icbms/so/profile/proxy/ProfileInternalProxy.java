package com.pineone.icbms.so.profile.proxy;

import com.pineone.icbms.so.bizcontext.pr.BizContextPresentation;
import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.contextmodel.logic.ContextModelLogicImpl;
import com.pineone.icbms.so.contextmodel.pr.ContextModelPresentation;
import com.pineone.icbms.so.profile.pr.ProfilePresentation;
import com.pineone.icbms.so.profile.pr.ProfileTransFormData;
import com.pineone.icbms.so.servicemodel.pr.ServiceModelPresentation;
import com.pineone.icbms.so.util.conversion.DataConversion;
import com.pineone.icbms.so.util.http.ClientService;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 11..
 * NOTE: 내부의 다른 모듈에 접근하기 위한 Proxy
 */

@Service
public class ProfileInternalProxy extends AbstractProfileProxy {
    //
    public static final Logger logger = LoggerFactory.getLogger(ProfileInternalProxy.class);

    @Autowired
            ContextModelPresentation contextModelPresentation;

    @Autowired
            ServiceModelPresentation serviceModelPresentation;

    @Autowired
            BizContextPresentation bizContextPresentation;

//    ContextModelPresentation contextModelPresentation = new ContextModelPresentation();
//    ServiceModelPresentation serviceModelPresentation = new ServiceModelPresentation();
//    BizContextPresentation bizContextPresentation = new BizContextPresentation();

    public static ProfileInternalProxy newProfileInternalProxy(){
        return new ProfileInternalProxy();
    }

    //NOTE: 컨텍스트모델 모듈에 연결해서 저장되어 있는 컨텍스트 모델들의 이름을 조회
    @Override
    public List<String> retrieveContextModelNameList() {
        //
        logger.info(LogPrint.outputInfoLogPrint());
        List<String> contextModelNameList = contextModelPresentation.retrieveContextModelList();
        return contextModelNameList;
    }

    //NOTE: 서비스모델 모듈에 연결해서 저장되어 있는 서비스모델들의 이름을 조회
    @Override
    public List<String> retrieveServiceModelNameList() {
        //
        logger.info(LogPrint.outputInfoLogPrint());
        List<String> serviceModelNameList = serviceModelPresentation.retrieveServiceModelIdList();
        return serviceModelNameList;
    }

    //NOTE: 비즈컨텍스트 모듈에 연결해서 저장되어 있는 비즈컨텍스트들의 이름을 조회
    @Override
    public List<String> retrieveBizContextList() {
        //
        logger.info(LogPrint.outputInfoLogPrint());
        List<String> bizContextNameList = bizContextPresentation.retrieveBizContextList();
        return bizContextNameList;
    }

    //NOTE: 컨텍스트모델 모듈에 연결해서 수신받은 인자된 응급상황이 있는지 조회
    @Override
    public boolean checkContextModelQueue() {
        //
        boolean flag = ContextModelLogicImpl.CONTEXT_MODEL_QUEUE.isEmpty();
        return flag;
    }

    //NOTE: 컨텍스트모델 모듈의 응급상황 데이터를 수신
    @Override
    public ContextModel retrieveContextModelQueueData() {
        //
        ContextModel contextModel = contextModelPresentation.retrieveContextModelQueueData();
        logger.info(LogPrint.outputInfoLogPrint() + ", contextModelId = " + contextModel.getId());
        return contextModel;
    }


    @Override
    public void registerScheduler(String profileId, int period) {
        ProfileTransFormData profileTransFormData = new ProfileTransFormData(profileId, period);
        String sendData = DataConversion.objectToString(profileTransFormData);
        ClientService clientService = new ClientService();
        clientService.requestPostService("http://localhost:10080/so/scheduler/create",sendData);
    }
}
