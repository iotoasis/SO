package com.pineone.icbms.so.virtualobject.pr;

import com.pineone.icbms.so.virtualobject.entity.ExternalVirtulaObject;
import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import com.pineone.icbms.so.virtualobject.logic.VirtualObjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value ="/virtualobject")
public class VirtualObjectPresentation {

    /**
     * VO 생성
     * VO 제어
     * VO 검색(By ID)
     * VO 검색(By Location)
     * VO 삭제
     */

    @Autowired
    private VirtualObjectManager virtualObjectManager;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createVirtualObject(@RequestBody ExternalVirtulaObject virtulaObject){
        virtualObjectManager.produceVirtualObject(virtulaObject);
    }


    @RequestMapping(value = "/control/{id}/{operation}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String controlVirtualObject(@PathVariable String id,@PathVariable String operation){
        return virtualObjectManager.controlDevice(id, operation);
    }

    @RequestMapping(value = "/resource/{id}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public VirtualObject searchVirtualObject(@PathVariable String id){
        return virtualObjectManager.searchVirtualObject(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<VirtualObject> searchVirtualObjectList(@PathVariable String id){
        return virtualObjectManager.searchVirtualObjectList(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteVirtualObject(@PathVariable String id){
        virtualObjectManager.deleteVirtualObject(id);
    }

}
