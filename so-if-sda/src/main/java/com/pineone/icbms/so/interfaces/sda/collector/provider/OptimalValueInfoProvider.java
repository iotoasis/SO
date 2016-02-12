package com.pineone.icbms.so.interfaces.sda.collector.provider;

import com.pineone.icbms.so.interfaces.sda.client.CollectorController;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseMapper;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseModel;
import com.pineone.icbms.so.iot.resources.value.DefaultValue;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;

/**
 * Created by Melvin on 2016. 1. 10..
 * 결과로 입력받은 정보들을 이용하여 URL을 만들고 특정 장소의 Optimal Value 를 수신받아 제공한다. <BR/>
 */
public class OptimalValueInfoProvider {
    public static final String host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-160/?p=";
//    public static final String basicContextId = "CM-1-1-110";


    /***
     *
     * 입력받은 Location, Context 정보를 이용하여 URL을 만들고 필요에 따른 OptimalValue 를 생성하여 반환<BR/>
     *

     * @param location
     * @return
     */

    public DefaultValue getOptimalValueFromSDA(DefaultLocation location){

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        DefaultValue value = new DefaultValue();

        String locationInfo = location.getUri();



        /**
         * Context 와 location 내용을 입력받아서 실제 디바이스 정보를 가지고 올 수 있는 URL을 생성.
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-160/?p=http://www.pineone.com/campus/DT0001
         *
         *  강의실 icbms:LR0001
         실험실 icbms:LB0001
         기숙사 icbms:DT0001
         */
        String getDeviceURL = host + locationInfo;

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getDeviceURL);


        /**
         * Json형태를 띄고 있는 String형 데이터를 Object로 변환
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** Value 정보만 추출하여 반환
         */
        value = new DefaultValue();
        value.setMaxValue(responseModel.getContent().get(0).getMax_value());
        value.setMinValue(responseModel.getContent().get(0).getMin_value());



        return value;
    }
}
