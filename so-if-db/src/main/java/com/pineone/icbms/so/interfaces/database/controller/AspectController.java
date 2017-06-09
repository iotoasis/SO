package com.pineone.icbms.so.interfaces.database.controller;//package com.pineone.icbms.so.interfaces.database.controller;
//
//import com.pineone.icbms.so.interfaces.database.controller.inputdata.AspectData;
//import com.pineone.icbms.so.interfaces.database.logic.itf.IAspectDAO;
//import com.pineone.icbms.so.interfaces.database.model.AspectForDB;
//import com.pineone.icbms.so.interfaces.database.ref.DataLossException;
//import com.pineone.icbms.so.interfaces.database.ref.DataValidation;
//import com.pineone.icbms.so.interfaces.database.ref.ResponseMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * Created by melvin on 2017. 3. 28..
// */
//
//// Aspect 관련 controller 기능 구현
//@Controller
//@RequestMapping(value = "/aspect")
//public class AspectController {
//
//    @Autowired
//    private IAspectDAO aspectDAO;
//
//    @Autowired
//    private ResponseMessage responseMessage;
//
//    @Autowired
//    private DataValidation dataValidation;
//
//    // Aspect Id 로 Aspect 내용 조회
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public AspectForDB retrieveAspect(@PathVariable long id) {
//        AspectForDB aspect = aspectDAO.retrieveAspect(id);
//        return aspect;
//    }
//
//    // SO에 등록되어 있는 전체 Aspect 내용 조회
//    @RequestMapping
//    @ResponseBody
//    public List<AspectForDB> retrieveAspectList() {
//        List<AspectForDB> aspectList = aspectDAO.retrieveAspectList();
//        return aspectList;
//    }
//
//    // SO에 Aspect 데이터 등록
//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseMessage createAspect(@RequestBody AspectData aspectData) {
//
//        try {
//            dataValidation.inspectAspectData(aspectData);
//        } catch (DataLossException e) {
//            responseMessage.setExceptionMessage(e.getMessage());
//            return responseMessage;
//        }
//
//        String createMessage = aspectDAO.createAspect(aspectData);
//        responseMessage.setMessage(createMessage);
//        return responseMessage;
//    }
//
//    // SO의 Aspect Data Update TODO  Update 정책 반영필요
//    @RequestMapping(value = "{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseMessage updateAspect(@PathVariable long id, @RequestBody AspectData aspectData) {
//
//        try {
//            dataValidation.inspectAspectData(aspectData);
//        } catch (DataLossException e) {
//            responseMessage.setExceptionMessage(e.getMessage());
//            return responseMessage;
//        }
//
//        String updateMessage = aspectDAO.updateAspect(id, aspectData);
//        responseMessage.setMessage(updateMessage);
//        return responseMessage;
//    }
//
//    // SO의 virtualObject Date Delete
//    @RequestMapping(value = "/delete/{id}")
//    @ResponseBody
//    public ResponseMessage deleteAspect(@PathVariable long id) {
//        String deleteMessage = aspectDAO.deleteAspect(id);
//        responseMessage.setMessage(deleteMessage);
//        return responseMessage;
//    }
//}
