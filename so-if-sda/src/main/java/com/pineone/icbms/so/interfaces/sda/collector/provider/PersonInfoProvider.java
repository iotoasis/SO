package com.pineone.icbms.so.interfaces.sda.collector.provider;

import com.pineone.icbms.so.interfaces.sda.client.CollectorController;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseMapper;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseModel;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;

import java.util.ArrayList;

/**
 * URL로 관련된 사람에 대한 정보를 제공 받기 위한 Provider
 * Created by Melvin on 2016. 1. 10..
 */
public class PersonInfoProvider {

    public static final String cm170Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-170/?p=";
    public static final String basicContextId = "CM-1-1-110";
    public static final String cm190Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-190/?p=";

    /***
     * 입력받은 Location, Context 정보를 이용하여 URL을 만들고 필요에 따른 Person 를 생성하여 반환<BR/>

     * @param location
     * @return
     */

    public ArrayList<DefaultStudent> getManagerInfoFromSDA(DefaultLocation location) {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        DefaultStudent student = new DefaultStudent();
        ArrayList<DefaultStudent> studentList = new ArrayList<>();

        String locationInfo = location.getUri();

        /**
         * Context 와 location 내용을 입력받아서 사람 정보를 가지고 올 수 있는 URL을 생성.
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-170/?p=http://www.pineone.com/campus/LB0001
         *
         *  강의실 icbms:LR0001
         실험실 icbms:LB0001
         기숙사 icbms:DT0001
         */
        String getPersonURL = cm170Host + locationInfo;

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getPersonURL);

        /**
         * Json형태를 띄고 있는 String형 데이터를 Object로 변환
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** Person 정보만 String 으로 추출하여 반환
         */

        for (int i = 0; i < responseModel.getContent().size(); i++) {

            student = new DefaultStudent();
            student.setUri(responseModel.getContent().get(i).getManager());
            studentList.add(student);
        }

        return studentList;
    }

    /***
     * 입력받은 시간 정보를 입력받아 URL을 만들고 알람을 울려야 하는 Person 를 생성하여 반환<BR/>
     *
     * @param time
     * @return
     */
    public ArrayList<DefaultStudent> getPersonInfoAboutAlarm(String time){

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        DefaultStudent student = new DefaultStudent();
        ArrayList<DefaultStudent> studentList = new ArrayList<>();

        /**
         * 시간 정보를 입력받아서 알람을 울려야 할 학생을 조회하는 URL생성
         */
        String getPersonURL = cm190Host + time;

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getPersonURL);

        /**
         * Json형태를 띄고 있는 String형 데이터를 Object로 변환
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** Person 정보만 String 으로 추출하여 반환
         */


        for (int i = 0; i < responseModel.getContent().size(); i++) {

            student = new DefaultStudent();
            student.setUri(responseModel.getContent().get(i).getUser_id());
            studentList.add(student);
        }

        return studentList;
    }
}

