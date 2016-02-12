package com.pineone.icbms.so.interfaces.sda.collector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Melvin on 2016. 1. 7..
 * SDA로 부터 수신받는 정보는 모두 content로 시작하기 때문에 , 파싱을 하기 위한 Class
 */
public class ResponseModel {

    /**
     * Content : 안에 들어있는 uri, loc, dev 등을 정의하는 ExpectJsonData를 리스트로 생성
     */
    private List<ExpectJsonData> content;

//    private List<DefaultLocation> content;


    //    class Uri{
//        private String uri;
//
//        public String getUri() {
//            return uri;
//        }
//
//        public void setUri(String uri) {
//            this.uri = uri;
//        }
//    }

    public List<ExpectJsonData> getContent() {
        return content;
    }

    public void setContent(List<ExpectJsonData> content) {
        this.content = content;
    }


    public List<ExpectJsonData> addDevice(ExpectJsonData devices) {
        if(this.content == null)
            this.content = new ArrayList<>();
        content.add(devices);
        return content;
    }
}
