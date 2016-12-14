package com.pineone.icbms.so.compositevo.pr;

import com.pineone.icbms.so.compositevo.entity.CompositeVirtualObject;
import com.pineone.icbms.so.compositevo.entity.VirtualObjectControlData;
import com.pineone.icbms.so.compositevo.logic.CompositeVirtualObjectManager;
import com.pineone.icbms.so.compositevo.ref.CompositeProfile;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/compositevo")
public class CompositeVirtualObjectPresentation {
    //
    /**
     * CVO 검색(list)
     * CVO 검색(By Id)
     * CVO 검색(By Location)
     * CVO 업데이트
     * CVO 삭제
     * CVO 실행(Id,Operation)
     * //CVO 실행(ID,functionality,operation)
     * CVO 생성.
     */

    public static final Logger logger = LoggerFactory.getLogger(CompositeVirtualObjectPresentation.class);

    @Autowired
    CompositeVirtualObjectManager compositeVirtualObjectManager;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void createCompositeVirtualObjectList(@RequestBody CompositeVirtualObjectTransFormObject dataObject){
        logger.info(LogPrint.inputInfoLogPrint() + " createCompositeVirtualObjectList");
        compositeVirtualObjectManager.createCompositeVO(compositeVirtualObjectMapping(dataObject));
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<CompositeVirtualObject> findCompositeVirtualObjectList(){
        logger.info(LogPrint.inputInfoLogPrint() + " findCompositeVirtualObjectList");
        return compositeVirtualObjectManager.searchCompositeVOList();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public CompositeVirtualObject findCompostieVirtualObject(@PathVariable String id){
        logger.info(LogPrint.inputInfoLogPrint() + " findCompostieVirtualObject");
        return compositeVirtualObjectManager.searchCompositeVO(id);
    }

    @RequestMapping(value = "/location/{place}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<CompositeVirtualObject> findLocationCompostieVirtualObject(@PathVariable String place){
        logger.info(LogPrint.inputInfoLogPrint() + " findLocationCompostieVirtualObject");
        return compositeVirtualObjectManager.searchLocationCompositeVO(place);
    }


    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCompostieVirtualObject(@RequestBody CompositeVirtualObjectTransFormObject dataObject){
        logger.info(LogPrint.inputInfoLogPrint() + " updateCompostieVirtualObject");
        if(dataObject == null) return;
        compositeVirtualObjectManager.updateCompositeVO(compositeVirtualObjectMapping(dataObject));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCompostieVirtualObject(@PathVariable String id){
        logger.info(LogPrint.inputInfoLogPrint() + " deleteCompostieVirtualObject");
        if(id == null) return;
        compositeVirtualObjectManager.deleteCompositeVO(id);
    }

    @RequestMapping(value = "/control",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void controlCompostieVirtualObject(@RequestBody VirtualObjectControlData virtualObjectControlData){
        logger.info(LogPrint.inputInfoLogPrint() + " controlCompostieVirtualObject");
        if(virtualObjectControlData == null) return;
        compositeVirtualObjectManager.executeCompositeVO(virtualObjectControlData.getId(), virtualObjectControlData.getDomain(), virtualObjectControlData.getFunctionality(), virtualObjectControlData.getOperation(), virtualObjectControlData.getSessionId());
    }

    private CompositeVirtualObject compositeVirtualObjectMapping(CompositeVirtualObjectTransFormObject compositeVirtualObjectDataObject){
        if(compositeVirtualObjectDataObject == null) return null;
        CompositeVirtualObject compositeVirtualObject = new CompositeVirtualObject(compositeVirtualObjectDataObject.getId(), compositeVirtualObjectDataObject.getName(), compositeVirtualObjectDataObject.getVoIdList(), compositeVirtualObjectDataObject.getLocation(), compositeVirtualObjectDataObject.getCreateTime(), compositeVirtualObjectDataObject.getModifiedTime(), compositeVirtualObjectDataObject.getDescription());
        if(!compositeVirtualObject.getId().startsWith(CompositeProfile.COMPOSITE_ID)){
            String cvoId = compositeVirtualObject.getId();
            compositeVirtualObject.setId(CompositeProfile.COMPOSITE_ID + cvoId);
        }
        return compositeVirtualObject ;
    }

    public VirtualObjectControlData settingVirtualObjectData(String compositevoId, String functionality, String operation, String sessionId){
        VirtualObjectControlData virtualObjectControlData = new VirtualObjectControlData();
        virtualObjectControlData.setId(compositevoId);
        virtualObjectControlData.setOperation(operation);
        virtualObjectControlData.setFunctionality(functionality);
        virtualObjectControlData.setSessionId(sessionId);
        return virtualObjectControlData;
    }

}
