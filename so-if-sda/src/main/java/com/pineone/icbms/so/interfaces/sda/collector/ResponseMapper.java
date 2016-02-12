package com.pineone.icbms.so.interfaces.sda.collector;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by Melvin on 2016. 1. 7..
 *
 * Json형태를 띄고 있는 String형 데이터를 ObjectMapper를 이용하여 오브젝트로 변환한다.
 */

public class ResponseMapper {
    ObjectMapper objectMapper = new ObjectMapper();
    ResponseModel responseModel = new ResponseModel();


    public ResponseModel getList(String JsonTypeStrData){

//        String jsonURl = url.replace("\\","");

//        try {
//            String content = objectMapper.readv.readValue(url, String.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        System.out.println(url);

        try {
            responseModel = objectMapper.readValue(JsonTypeStrData, ResponseModel.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseModel;
    }

}
