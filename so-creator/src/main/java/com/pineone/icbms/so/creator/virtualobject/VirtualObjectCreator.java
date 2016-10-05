package com.pineone.icbms.so.creator.virtualobject;

import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import com.pineone.icbms.so.virtualobject.pr.VirtualObjectPresentation;
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
 * NOTE : VO 저작시 필요한 내용들을 Restful 을 이용해서 외부에 노출
 */

@RestController
@RequestMapping(value ="/virtualobject")
@ResponseStatus(value = HttpStatus.OK)
public class VirtualObjectCreator {

    @Autowired
    VirtualObjectPresentation virtualObjectPresentation;

    //NOTE: VirtualObject Id List 조회
    @RequestMapping(value = "/id",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<String> retrieveVirtualObjectIdList(){
        List<String> voIdList = new ArrayList<>();
        List<VirtualObject> virtualObjectList = virtualObjectPresentation.searchVirtualObjectList();
        for(VirtualObject virtualObject : virtualObjectList){
            voIdList.add(virtualObject.getVoId());
        }
        return voIdList;
    }

    //NOTE: VirtualObject List 조회
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<VirtualObject> retrieveVOList(){
        List<VirtualObject> virtualObjectList = virtualObjectPresentation.searchVirtualObjectList();
        return virtualObjectList;
    }

}
