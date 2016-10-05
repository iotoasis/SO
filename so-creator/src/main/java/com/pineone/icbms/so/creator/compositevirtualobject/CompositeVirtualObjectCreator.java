package com.pineone.icbms.so.creator.compositevirtualobject;

import com.pineone.icbms.so.compositevo.entity.CompositeVirtualObject;
import com.pineone.icbms.so.compositevo.pr.CompositeVirtualObjectPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 10. 5..
 * NOTE : CVO 저작시 필요한 내용들을 Restful 을 이용해서 외부에 노출
 */

@RestController
@RequestMapping(value ="/compositevo")
@ResponseStatus(value = HttpStatus.OK)
public class CompositeVirtualObjectCreator {

    @Autowired
    CompositeVirtualObjectPresentation compositeVirtualObjectPresentation;

    //NOTE: CompositeVO Id List 조회
    @RequestMapping(value = "/id",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<String> retrieveCompositeCVOIdList(){
        List<String> compositeVoIdList = new ArrayList<>();
        List<CompositeVirtualObject> compositeVirtualObjectList = compositeVirtualObjectPresentation.findCompositeVirtualObjectList();
        for(CompositeVirtualObject compositeVirtualObject : compositeVirtualObjectList){
            compositeVoIdList.add(compositeVirtualObject.getId());
        }
        return compositeVoIdList;
    }

    //NOTE: CompositeVO List 조회
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<CompositeVirtualObject> retrieveCVOList(){
        List<CompositeVirtualObject> compositeVirtualObjectList = compositeVirtualObjectPresentation.findCompositeVirtualObjectList();
        return compositeVirtualObjectList;
    }
}
