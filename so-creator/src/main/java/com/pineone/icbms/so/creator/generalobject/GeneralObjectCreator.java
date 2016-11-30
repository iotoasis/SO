package com.pineone.icbms.so.creator.generalobject;

import com.pineone.icbms.so.compositevo.entity.CompositeVirtualObject;
import com.pineone.icbms.so.compositevo.pr.CompositeVirtualObjectPresentation;
import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import com.pineone.icbms.so.virtualobject.pr.VirtualObjectPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 10. 5..
 * NOTE : GeneralObject(VO + CVO) 저작시 필요한 내용들을 노출
 */
@RestController
@RequestMapping(value = "/generalobject1")
@ResponseStatus(value = HttpStatus.OK)
public class GeneralObjectCreator {

    @Autowired
    VirtualObjectPresentation virtualObjectPresentation;

    @Autowired
    CompositeVirtualObjectPresentation compositeVirtualObjectPresentation;

    //NOTE : GeneralObject Id List 조회
    @RequestMapping(value = "/id",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<String> retrieveGeneralObjectIdList() {
        List<String> generalObjectIdList = new ArrayList<>();
        List<VirtualObject> virtualObjectList = virtualObjectPresentation.searchVirtualObjectList();
        List<CompositeVirtualObject> compositeVirtualObjectList = compositeVirtualObjectPresentation.findCompositeVirtualObjectList();

        for (VirtualObject virtualObject : virtualObjectList) {
            generalObjectIdList.add(virtualObject.getId());
        }
        for (CompositeVirtualObject compositeVirtualObject : compositeVirtualObjectList) {
            generalObjectIdList.add(compositeVirtualObject.getId());
        }
        return generalObjectIdList;
    }

    //NOTE : GeneralObject List 조회
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Object> retrieveGeneralObjectList(){
        List<Object> generalObjectList = new ArrayList<>();
        List<VirtualObject> virtualObjectList = virtualObjectPresentation.searchVirtualObjectList();
        List<CompositeVirtualObject> compositeVirtualObjectList = compositeVirtualObjectPresentation.findCompositeVirtualObjectList();

        for(VirtualObject virtualObject : virtualObjectList){
            generalObjectList.add(virtualObject);
        }
        for(CompositeVirtualObject compositeVirtualObject : compositeVirtualObjectList){
            generalObjectList.add(compositeVirtualObject);
        }
        return generalObjectList;
    }

    //NOTE : GeneralObject 의 FunctionalityList 조회
    @RequestMapping(value = "/{generalObjectId}" ,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<String> retrieveFunctionalityList(@PathVariable String generalObjectId){
        List<String> functionalityList = new ArrayList<>();
        if((virtualObjectPresentation.searchVirtualObject(generalObjectId)) != null){
            functionalityList.add(virtualObjectPresentation.searchVirtualObject(generalObjectId).getFunctionality());
        }
        else{
            //TODO : CVO Functionality List 조회
//            functionalityList.add(compositeVirtualObjectPresentation.findCompostieVirtualObject(generalObjectId).getFunctionality);
        }
        return functionalityList;
    }
}
