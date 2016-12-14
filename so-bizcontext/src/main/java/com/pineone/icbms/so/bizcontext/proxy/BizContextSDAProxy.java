package com.pineone.icbms.so.bizcontext.proxy;

import com.google.gson.Gson;
import com.pineone.icbms.so.bizcontext.pr.BizContextPresentation;
import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.util.address.AddressStore;
import com.pineone.icbms.so.util.address.ContextAddress;
import com.pineone.icbms.so.util.http.ClientService;
import com.pineone.icbms.so.util.logprint.LogPrint;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by melvin on 2016. 8. 4..
 */

@Service
public class BizContextSDAProxy implements BizContextProxy{

    public static final Logger logger = LoggerFactory.getLogger(BizContextPresentation.class);

    @Autowired
    ContextAddress contextAddress;

    @Autowired
    ClientService clientService;

    public static BizContextSDAProxy newBizContextProxy(){
        return new BizContextSDAProxy();
    }

    //NOTE: SDA 에 현재 사용량을 조회 (전력외 다른 Aspect 추가시 파라미터 추가)
    @Override
    public int retrieveCurrentValue(Domain domain) {
        //
//        contextAddress = ContextAddress.newContextAddress();
        logger.info(LogPrint.outputInfoLogPrint());
        IHttpResponseMessage message = clientService.requestGetService
                (contextAddress.getServerAddress(ContextAddress.SDA_SERVER) + AddressStore.RETRIEVE_CURRENT_VALUE + "/" + domain.getName());
        String response = new Gson().toJson(message);
        int currentValue = Integer.parseInt(response);
        return currentValue;
    }

    //NOTE: SDA 에 과거 사용량을 조회 (전력외 다른 Aspect 추가시 파라미터 추가,  시간등록 필요시 Aspect 추가)
    @Override
    public int retrievePastValue(Domain domain) {
        //
//        contextAddress = ContextAddress.newContextAddress();
        logger.info(LogPrint.outputInfoLogPrint());
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getServerAddress(ContextAddress.SDA_SERVER) + AddressStore.RETRIEVE_PAST_VALUE + "/" + domain.getName());
        String response = new Gson().toJson(message);
        int pastValue = Integer.parseInt(response);
        return pastValue;
    }

    // NOTE : SDA 에 모든 디바이스의 예상 총 전력 소비량 조회
    @Override
    public int retrieveAllDeviceUseAmount(Domain domain) {
        //
//        contextAddress = ContextAddress.newContextAddress();
        logger.info(LogPrint.outputInfoLogPrint());
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getServerAddress(ContextAddress.SDA_SERVER) + AddressStore.RETRIEVE_USE_AMOUNT + "/" + domain.getName());
        String response = new Gson().toJson(message);
        int useAmount = Integer.parseInt(response);
        return useAmount;
    }

    //NOTE : SDA 에 목표 전력 사용량을 조회
    @Override
    public int retrieveObjectValue(Domain domain) {
        //
//        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getServerAddress(ContextAddress.SDA_SERVER) + AddressStore.RETRIEVE_OBJECT_VALUE + "/" + domain.getName());
        String response = new Gson().toJson(message);
        int objectValue = Integer.parseInt(response);
        return objectValue;
    }

    //NOTE : SDA 에 현재 전산실의 PC 수량을 조회
    @Override
    public int retrieveCurrentClassPCAmount(Domain domain) {
        //
//        contextAddress = ContextAddress.newContextAddress();
        logger.info(LogPrint.outputInfoLogPrint());
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getServerAddress(ContextAddress.SDA_SERVER) + AddressStore.RETRIEVE_CURRENT_PC + "/" + domain.getName());
        String response = new Gson().toJson(message);
        int currentPC = Integer.parseInt(response);
        return currentPC;
    }

    //NOTE : SDA 에 전산실의 다음 수업에 필요한 PC 수량을 조회
    @Override
    public int retrieveNextClassPCAmount(Domain domain) {
        //
//        contextAddress = ContextAddress.newContextAddress();
        logger.info(LogPrint.outputInfoLogPrint());
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getServerAddress(ContextAddress.SDA_SERVER) + AddressStore.RETRIEVE_NEXT_PC + "/" + domain.getName());
        String response = new Gson().toJson(message);
        int nextPC = Integer.parseInt(response);
        return nextPC;
    }

    //NOTE : SDA 에 현재 전산실의 마우스 수량을 조회
    @Override
    public int retrieveCurrentClassMouseAmount(Domain domain) {
        //
//        contextAddress = ContextAddress.newContextAddress();
        logger.info(LogPrint.outputInfoLogPrint());
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getServerAddress(ContextAddress.SDA_SERVER) + AddressStore.RETRIEVE_CURRENT_MOUSE + "/" + domain.getName());
        String response = new Gson().toJson(message);
        int currentMouse = Integer.parseInt(response);
        return currentMouse;
    }

    //NOTE : SDA 에 전산실의 다음 수업에 필요한 마우스 수량을 조회
    @Override
    public int retrieveNextClassMouseAmount(Domain domain) {
        //
//        contextAddress = ContextAddress.newContextAddress();
        logger.info(LogPrint.outputInfoLogPrint());
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getServerAddress(ContextAddress.SDA_SERVER) + AddressStore.RETRIEVE_NEXT_MOUSE + "/" + domain.getName());
        String response = new Gson().toJson(message);
        int nextMouse = Integer.parseInt(response);
        return nextMouse;
    }

    //NOTE : SDA 에 현재 전산실의 키보드 수량을 조회
    @Override
    public int retrieveCurrentClassKeyBoardAmount(Domain domain) {
        //
//        contextAddress = ContextAddress.newContextAddress();
        logger.info(LogPrint.outputInfoLogPrint());
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getServerAddress(ContextAddress.SDA_SERVER) + AddressStore.RETRIEVE_CURRENT_KEYBOARD + "/" + domain.getName());
        String response = new Gson().toJson(message);
        int currentKeyBoard = Integer.parseInt(response);
        return currentKeyBoard;
    }

    //NOTE : SDA 에 현재 전산실의 다음 수업에 필요한 키보드 수량을 조회
    @Override
    public int retrieveNextClassKeyBoardAmount(Domain domain) {
        //
//        contextAddress = ContextAddress.newContextAddress();
        logger.info(LogPrint.outputInfoLogPrint());
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getServerAddress(ContextAddress.SDA_SERVER) + AddressStore.RETRIEVE_NEXT_KEYBOARD + "/" + domain.getName());
        String response = new Gson().toJson(message);
        int nextKeyBoard = Integer.parseInt(response);
        return nextKeyBoard;
    }
}
