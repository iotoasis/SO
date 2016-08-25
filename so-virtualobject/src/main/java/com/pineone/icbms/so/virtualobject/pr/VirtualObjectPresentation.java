package com.pineone.icbms.so.virtualobject.pr;

import com.pineone.icbms.so.virtualobject.entity.ServiceControl;
import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import com.pineone.icbms.so.virtualobject.entity.VirtualObjectRequestControl;
import com.pineone.icbms.so.virtualobject.entity.VirtualObjectTransFormObject;
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
    public void createVirtualObject(@RequestBody VirtualObjectTransFormObject virtulaObject){
        virtualObjectManager.produceVirtualObject(virtualObjectMapping(virtulaObject));
    }

    @RequestMapping(value = "/requestcontrol",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String requestControlVirtualObject(@RequestBody VirtualObjectRequestControl virtualObjectRequestControl){
        //
        System.out.println("\n**********  VirtualObject Presentation RequestVOControl  **********");
        System.out.println("Response virtualObjectID = " + virtualObjectRequestControl.getVoId());
        System.out.println("Response virtualObjectOperation = " + virtualObjectRequestControl.getOperation());

        return virtualObjectManager.requestControlDevice(virtualObjectRequestControl.getVoId(), virtualObjectRequestControl.getOperation());
    }


    @RequestMapping(value = "/control",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String controlVirtualObject(@RequestBody List<ServiceControl> serviceControls){
        return virtualObjectManager.controlDevice(serviceControls);
    }



    @RequestMapping(value = "/resource/{id}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public VirtualObject searchVirtualObject(@PathVariable String id){
        return virtualObjectManager.searchVirtualObject(id);
    }

    @RequestMapping(value = "/{location}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<VirtualObject> searchVirtualObjectList(@PathVariable String location){
        return virtualObjectManager.searchVirtualObjectList(location);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<VirtualObject> searchVirtualObjectList(){
        return virtualObjectManager.searchVirtualObjectList();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteVirtualObject(@PathVariable String id){
        virtualObjectManager.deleteVirtualObject(id);
    }

    private VirtualObject virtualObjectMapping(VirtualObjectTransFormObject eVirtualObject)
    {
        VirtualObject virtualObject = new VirtualObject();
        virtualObject.setVoId(eVirtualObject.getVoId());
        virtualObject.setDeviceId(eVirtualObject.getDeviceId());
        virtualObject.setDeviceService(eVirtualObject.getDeviceService());
        virtualObject.setFunctionality(eVirtualObject.getFunctionality());
        virtualObject.setVoCommand(eVirtualObject.getVoCommand());
        virtualObject.setVoCreateTime(eVirtualObject.getVoCreateTime());
        virtualObject.setVoExpiredTime(eVirtualObject.getVoExpiredTime());
        virtualObject.setVoDescription(eVirtualObject.getVoDescription());
        virtualObject.setVoName(eVirtualObject.getVoName());
        virtualObject.setVoLocation(eVirtualObject.getVoLocation());
        return virtualObject;
    }

    @RequestMapping(value = "/testSetUp",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void testSetUp(){
        //
        virtualObjectManager.testSetUp();
    }


}
