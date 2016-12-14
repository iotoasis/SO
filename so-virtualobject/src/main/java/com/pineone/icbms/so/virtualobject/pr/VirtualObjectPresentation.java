package com.pineone.icbms.so.virtualobject.pr;

import com.pineone.icbms.so.util.logprint.LogPrint;
import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import com.pineone.icbms.so.virtualobject.entity.VirtualObjectTransFormObject;
import com.pineone.icbms.so.virtualobject.logic.VirtualObjectManager;
import com.pineone.icbms.so.virtualobject.ref.VirtualObjectProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value ="/virtualobject")
@ResponseStatus(value = HttpStatus.OK)
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

    @RequestMapping(value = "/control",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String requestControlVirtualObject(@RequestBody VirtualObjectTransFormObject virtualObjectTransFormObject){
        logger.info(LogPrint.inputInfoLogPrint());
        //
        return virtualObjectManager.requestControlDevice(virtualObjectTransFormObject.getId(), virtualObjectTransFormObject.getVoCommand(), virtualObjectTransFormObject.getSessionId());
    }

/*
    @RequestMapping(value = "/requestcontrol",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String controlVirtualObject(@RequestBody List<ServiceControl> serviceControls){
        logger.info(LogPrint.inputInfoLogPrint());
        return virtualObjectManager.controlDevice(serviceControls);
    }
*/



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

    private VirtualObject virtualObjectMapping(VirtualObjectTransFormObject virtualObjectDataObject)
    {
        if(virtualObjectDataObject == null){
            return null;
        }
        VirtualObject virtualObject = new VirtualObject(virtualObjectDataObject.getId(),virtualObjectDataObject.getVoName(),virtualObjectDataObject.getFunctionality(),virtualObjectDataObject.getVoDescription(),virtualObjectDataObject.getVoCreateTime(),virtualObjectDataObject.getVoExpiredTime(),virtualObjectDataObject.getDeviceService(),virtualObjectDataObject.getDeviceId(),virtualObjectDataObject.getVoCommand(),virtualObjectDataObject.getVoLocation());
        if(!virtualObject.getId().startsWith(VirtualObjectProfile.VIRTUALOBJECT_ID)){
            String voId = virtualObject.getId();
            virtualObject.setId(VirtualObjectProfile.VIRTUALOBJECT_ID + voId);
        }
        return virtualObject;
    }

    public VirtualObjectTransFormObject settingVirtualObjectData(String id, String operation,String sessionId){
        VirtualObjectTransFormObject object = new VirtualObjectTransFormObject();
        object.setId(id);
        object.setVoCommand(operation);
        object.setSessionId(sessionId);
        return object;
    }


}
