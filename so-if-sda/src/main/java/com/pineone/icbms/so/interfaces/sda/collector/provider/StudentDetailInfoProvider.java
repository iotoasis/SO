package com.pineone.icbms.so.interfaces.sda.collector.provider;

import com.pineone.icbms.so.interfaces.sda.client.CollectorController;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseMapper;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseModel;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;

import java.util.ArrayList;

/**
 * Created by Melvin on 2016. 1. 10..
 * 사람의 상세 정보를 받기 위한 객체
 */
public class StudentDetailInfoProvider {

    public static final String host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-220/?p=";
    public static final String basicContextId = "CM-1-1-110";



    /***
     * 입력받은 context, student 정보를 이용하여 URL을 만들고 필요에 따른 Person 를 생성하여 반환<BR/>
     *

     * @param student
     * @return
     */

    public ArrayList<DefaultStudent> getStudentExtraInfoFromSDA(DefaultStudent student) {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        ArrayList<DefaultStudent> studentList = new ArrayList<>();

        String personInfo = student.getUri();

        /**
         * Context 와 student정보를 받아 Student의 상세 정보를 조회<BR/>
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-220/?p=http://www.pineone.com/campus/Student_S00001<BR/>
         *
         */
        String getDeviceURL = host + personInfo;

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getDeviceURL);

        /**
         * Json형태를 띄고 있는 String형 데이터를 Object로 변환
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** 학생의 상세 정보를 리스트에 넣어 반환
         */

        for (int i = 0; i < responseModel.getContent().size(); i++) {

            student = new DefaultStudent();
            student.setId(responseModel.getContent().get(i).getUser_id());
            student.setPhone(responseModel.getContent().get(i).getPhone());
            student.setName(responseModel.getContent().get(i).getName());
            student.setStudentId(responseModel.getContent().get(i).getStudent_id());

            studentList.add(student);
        }

        return studentList;
    }
}
