package com.pineone.icbms.so.virtualobject.pr;

import com.pineone.icbms.so.util.logprint.LogPrint;
import com.pineone.icbms.so.virtualobject.entity.ServiceControl;
import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import com.pineone.icbms.so.virtualobject.entity.VirtualObjectTransFormObject;
import com.pineone.icbms.so.virtualobject.logic.VirtualObjectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger logger = LoggerFactory.getLogger(VirtualObjectPresentation.class);

    @Autowired
    private VirtualObjectManager virtualObjectManager;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createVirtualObject(@RequestBody VirtualObjectTransFormObject virtulaObject){
        logger.info(LogPrint.inputInfoLogPrint());
        virtualObjectManager.produceVirtualObject(virtualObjectMapping(virtulaObject));
    }

    @RequestMapping(value = "/requestcontrol",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String requestControlVirtualObject(@RequestBody VirtualObjectTransFormObject virtualObjectTransFormObject){
        logger.info(LogPrint.inputInfoLogPrint());
        //
        System.out.println("\n**********  VirtualObject Presentation RequestVOControl  **********");
        System.out.println("Response virtualObjectID = " + virtualObjectTransFormObject.getVoId());
        System.out.println("Response virtualObjectOperation = " + virtualObjectTransFormObject.getVoCommand());

        return virtualObjectManager.requestControlDevice(virtualObjectTransFormObject.getVoId(), virtualObjectTransFormObject.getVoCommand());
    }


    @RequestMapping(value = "/control",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String controlVirtualObject(@RequestBody List<ServiceControl> serviceControls){
        logger.info(LogPrint.inputInfoLogPrint());
        return virtualObjectManager.controlDevice(serviceControls);
    }



    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public VirtualObject searchVirtualObject(@PathVariable String id){
        logger.info(LogPrint.inputInfoLogPrint());
        return virtualObjectManager.searchVirtualObject(id);
    }

    @RequestMapping(value = "/location/{place}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<VirtualObject> searchVirtualObjectList(@PathVariable String place){
        logger.info(LogPrint.inputInfoLogPrint());
        return virtualObjectManager.searchVirtualObjectList(place);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<VirtualObject> searchVirtualObjectList(){
        logger.info(LogPrint.inputInfoLogPrint());
        return virtualObjectManager.searchVirtualObjectList();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteVirtualObject(@PathVariable String id){
        logger.info(LogPrint.inputInfoLogPrint());
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

    public VirtualObjectTransFormObject settingVirtualObjectData(String id, String operation){
        VirtualObjectTransFormObject object = new VirtualObjectTransFormObject();
        object.setVoId(id);
        object.setVoCommand(operation);
        return object;
    }


}
