package com.pineone.icbms.so.bizcontext.proxy;

import com.google.gson.Gson;
import com.pineone.icbms.so.util.address.AddressStore;
import com.pineone.icbms.so.util.address.ContextAddress;
import com.pineone.icbms.so.util.http.ClientService;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;

/**
 * Created by melvin on 2016. 8. 4..
 */
public class BizContextSDAProxy implements BizContextProxy{

    ContextAddress contextAddress = ContextAddress.newContextAddress();
    ClientService clientService = new ClientService();

    public static BizContextSDAProxy newBizContextProxy(){
        return new BizContextSDAProxy();
    }

    //NOTE: SDA 에 현재 사용량을 조회 (전력외 다른 Aspect 추가시 파라미터 추가)
    @Override
    public int retrieveCurrentValue() {
        //
        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestGetService
                (contextAddress.getAddress() + AddressStore.RETRIEVE_CURRENT_VALUE);
        String response = new Gson().toJson(message);
        int currentValue = Integer.parseInt(response);
        return currentValue;
    }

    //NOTE: SDA 에 과거 사용량을 조회 (전력외 다른 Aspect 추가시 파라미터 추가,  시간등록 필요시 Aspect 추가)
    @Override
    public int retrievePastValue() {
        //
        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_PAST_VALUE);
        String response = new Gson().toJson(message);
        int pastValue = Integer.parseInt(response);
        return pastValue;
    }

    // NOTE : SDA 에 모든 디바이스의 예상 총 전력 소비량 조회
    @Override
    public int retrieveAllDeviceUseAmount() {
        //
        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_USE_AMOUNT);
        String response = new Gson().toJson(message);
        int useAmount = Integer.parseInt(response);
        return useAmount;
    }

    //NOTE : SDA 에 목표 전력 사용량을 조회
    @Override
    public int retrieveObjectValue() {
        //
        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_OBJECT_VALUE);
        String response = new Gson().toJson(message);
        int objectValue = Integer.parseInt(response);
        return objectValue;
    }

    //NOTE : SDA 에 현재 전산실의 PC 수량을 조회
    @Override
    public int retrieveCurrentClassPCAmount() {
        //
        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_CURRENT_PC);
        String response = new Gson().toJson(message);
        int currentPC = Integer.parseInt(response);
        return currentPC;
    }

    //NOTE : SDA 에 전산실의 다음 수업에 필요한 PC 수량을 조회
    @Override
    public int retrieveNextClassPCAmount() {
        //
        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_NEXT_PC);
        String response = new Gson().toJson(message);
        int nextPC = Integer.parseInt(response);
        return nextPC;
    }

    //NOTE : SDA 에 현재 전산실의 마우스 수량을 조회
    @Override
    public int retrieveCurrentClassMouseAmount() {
        //
        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_CURRENT_MOUSE);
        String response = new Gson().toJson(message);
        int currentMouse = Integer.parseInt(response);
        return currentMouse;
    }

    //NOTE : SDA 에 전산실의 다음 수업에 필요한 마우스 수량을 조회
    @Override
    public int retrieveNextClassMouseAmount() {
        //
        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_NEXT_MOUSE);
        String response = new Gson().toJson(message);
        int nextMouse = Integer.parseInt(response);
        return nextMouse;
    }

    //NOTE : SDA 에 현재 전산실의 키보드 수량을 조회
    @Override
    public int retrieveCurrentClassKeyBoardAmount() {
        //
        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_CURRENT_KEYBOARD);
        String response = new Gson().toJson(message);
        int currentKeyBoard = Integer.parseInt(response);
        return currentKeyBoard;
    }

    //NOTE : SDA 에 현재 전산실의 다음 수업에 필요한 키보드 수량을 조회
    @Override
    public int retrieveNextClassKeyBoardAmount() {
        //
        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_NEXT_KEYBOARD);
        String response = new Gson().toJson(message);
        int nextKeyBoard = Integer.parseInt(response);
        return nextKeyBoard;
    }
}
