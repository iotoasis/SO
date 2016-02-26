package com.pineone.icbms.so.interfaces.sda.collector;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by Melvin on 2016. 1. 7..
 *
 * Convert To Object from Jason Type Sting Data<BR/>
 */

public class ResponseMapper {
    ObjectMapper objectMapper = new ObjectMapper();
    ResponseModel responseModel = new ResponseModel();


    public ResponseModel getList(String JsonTypeStrData){

        try {
            responseModel = objectMapper.readValue(JsonTypeStrData, ResponseModel.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseModel;
    }

}
