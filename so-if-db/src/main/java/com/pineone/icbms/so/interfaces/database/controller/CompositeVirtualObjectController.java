//package com.pineone.icbms.so.interfaces.database.controller;
//
//import com.pineone.icbms.so.interfaces.database.controller.inputdata.CompositeVirtualObjectData;
//import com.pineone.icbms.so.interfaces.database.controller.inputdata.VirtualObjectData;
//import com.pineone.icbms.so.interfaces.database.logic.itf.ICompositeVirtualObjectDAO;
//import com.pineone.icbms.so.interfaces.database.ref.DataLossException;
//import com.pineone.icbms.so.interfaces.database.ref.DataValidation;
//import com.pineone.icbms.so.interfaces.database.ref.ResponseMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
///**
// * Created by melvin on 2017. 4. 10..
// */
//
//
//@Controller
//@RequestMapping(value = "/cvo")
//public class CompositeVirtualObjectController {
//
//    @Autowired
//    private ICompositeVirtualObjectDAO compositeVirtualObjectDAO;
//
//    @Autowired
//    private ResponseMessage responseMessage;
//
//    @Autowired
//    private DataValidation dataValidation;
//
//    @RequestMapping(value = "{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseMessage updateVirtualObject(@PathVariable String id, @RequestBody CompositeVirtualObjectData compositeVirtualObjectData){
//
//        String updateMessage = (compositeVirtualObjectDAO.updateCompositeVirtualObject(id, compositeVirtualObjectData)).toString();
//        responseMessage.setMessage(updateMessage);
//        return responseMessage;
//    }
//
//}
