package com.pineone.icbms.so.servicemodel;

import com.pineone.icbms.so.contextinformation.entity.ContextInformation;
import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.contextmodel.entity.Domain;
import com.pineone.icbms.so.contextmodel.pr.ContextModelPresentation;
import com.pineone.icbms.so.contextmodel.ref.ResponseMessage;
import com.pineone.icbms.so.profile.entity.Profile;
import com.pineone.icbms.so.profile.logic.ProfileLogicImpl;
import com.pineone.icbms.so.profile.pr.ProfilePresentation;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 16..
 */
public class ProfileExecuteTest {
    //

    ContextModelPresentation contextModelPresentation = new ContextModelPresentation();

    @Before
    public void setData() throws Exception {

    }

    public static void main(String[] args) {
        //
        ContextModelPresentation contextModelPresentation = new ContextModelPresentation();
        ContextModel contextModel = new ContextModel();

        //NOTE: 도메인 셋팅
        Domain domain1 = new Domain("강의실", "http://강의실");
        Domain domain2 = new Domain("기숙사", "http://기숙사");
        List<Domain> domainList = new ArrayList<>();
        domainList.add(domain1);
        domainList.add(domain2);

        //NOTE : CI 셋팅
        ContextInformation contextInformation1 = new ContextInformation();
        ContextInformation contextInformation2 = new ContextInformation();
        contextInformation1.setName("고온상황");
        contextInformation2.setName("사람없음");
        List<String> contextInformationNameList = new ArrayList<>();
        contextInformationNameList.add(contextInformation1.getName());
        contextInformationNameList.add(contextInformation2.getName());

        contextModel.setDomainList(domainList);
        contextModel.setContextInformationList(contextInformationNameList);
        contextModel.setName("화재상황");
        contextModel.setContextType("Emergency");

        contextModelPresentation.registerGeneralContextController(contextModel);


        Thread profileThread = new Thread(new ProfileLogicImpl());
        profileThread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        ContextModel contextModel1 = contextModelPresentation.retrieveContextModelDetailController("화재상황");
        contextModelPresentation.emergencyContextModel(contextModel1);


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        ContextModel contextModel2 = contextModelPresentation.retrieveContextModelDetailController("화재상황");
        ResponseMessage responseMessage = contextModelPresentation.emergencyContextModel(contextModel2);
        System.out.println(responseMessage.getMessage());

    }

//    @Test
//    public void name() throws Exception {
//        ContextModel contextModel = new ContextModel();
//        ContextModelPresentation contextModelPresentation = new ContextModelPresentation();
//
//        //NOTE: 도메인 셋팅
//        Domain domain1 = new Domain("강의실", "http://강의실");
//        Domain domain2 = new Domain("기숙사", "http://기숙사");
//        List<Domain> domainList = new ArrayList<>();
//        domainList.add(domain1);
//        domainList.add(domain2);
//
//        //NOTE : CI 셋팅
//        ContextInformation contextInformation1 = new ContextInformation();
//        ContextInformation contextInformation2 = new ContextInformation();
//        contextInformation1.setName("고온상황");
//        contextInformation2.setName("사람없음");
//        List<String> contextInformationNameList = new ArrayList<>();
//        contextInformationNameList.add(contextInformation1.getName());
//        contextInformationNameList.add(contextInformation2.getName());
//
//        contextModel.setDomainList(domainList);
//        contextModel.setContextInformationList(contextInformationNameList);
//        contextModel.setName("화재상황");
//        contextModel.setContextType("Emergency");
//
//        contextModelPresentation.registerGeneralContextController(contextModel);
//
//        ContextModel contextModel1 = contextModelPresentation.retrieveContextModelDetailController("화재상황");
//        ResponseMessage responseMessage = contextModelPresentation.emergencyContextModel(contextModel1);
//        System.out.println(responseMessage.getMessage());
//    }
}
