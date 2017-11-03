package com.pineone.icbms.so.web.tasks;

import com.pineone.icbms.so.interfaces.database.dao.VirtualObjectDao;
import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.service.DataBaseStore;
import com.pineone.icbms.so.interfaces.sda.handle.SdaManager;
import com.pineone.icbms.so.interfaces.sda.model.AspectForIf;
import com.pineone.icbms.so.interfaces.sda.model.FunctionForIf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jonghee on 2017. 8. 22..
 */
@Component
public class ScheduledTasks {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    SdaManager sdaManager;

    @Autowired
    VirtualObjectDao virtualObjectDao;

    /*
     * virtual object 동기화
     * sda에서 function 과 aspect 로 조회한 결과로 so 에 저장된 vo 를 찾아 없으면 생성한다
     *
     * 초 0-59 , - * /
     * 분 0-59 , - * /
     * 시 0-23 , - * /
     * 일 1-31 , - * ? / L W
     * 월 1-12 or JAN-DEC , - * /
     * 요일 1-7 or SUN-SAT , - * ? / L #
     * 년(옵션) 1970-2099 , - * /
     * * : 모든 값
     * ? : 특정 값 없음
     * - : 범위 지정에 사용
     * , : 여러 값 지정 구분에 사용
     * / : 초기값과 증가치 설정에 사용
     * L : 지정할 수 있는 범위의 마지막 값
     * W : 월~금요일 또는 가장 가까운 월/금요일
     * # : 몇 번째 무슨 요일 2#1 => 첫 번째 월요일
     */
    @Scheduled(cron="0 0 1 * * *")  // 01:00:00 초에 시작
//    @Scheduled(cron="0 */3 * * * *")
    public void synchronizeVirtualObject() {

//        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//        System.out.println("[fixedRate]The time is now " + dateFormat.format(new Date()));

        // get function from sda
        List<FunctionForIf> functionList = sdaManager.retrieveFunctionList();
        List<AspectForIf> aspectList = sdaManager.retrieveAspectList();

        for (FunctionForIf function : functionList) {
            // get aspect from sda
            //List<AspectForIf> aspectList = sdaManager.retrieveAspectListByFunction(function.getFunction());

            for (AspectForIf aspect : aspectList) {
                // retrieve functionality_id and aspect_id
                //log.info("Function: {}, Aspect : {}", function, aspect);
                try {
                    VirtualObjectForDB virtualObjectForDB = virtualObjectDao.retrieveVirtualObjectByFunctionAndAspect(
                            function.getFunction(), aspect.getAspect());
    
                    // create virtual object
                    if (virtualObjectForDB == null) {
                        String prefix = "VO-";
                        String functionalityId = function.getFunction().substring(function.getFunction().lastIndexOf("/") + 1);
                        String aspectId = aspect.getAspect().substring(aspect.getAspect().lastIndexOf("/") + 1);
                        aspectId = aspectId.replaceAll("-aspect", "");
                        String id = prefix + functionalityId + "-" + aspectId;
    
                        //log.info("VO id : {}", id);
    
                        VirtualObjectForDB newVirtualObjectForDB = new VirtualObjectForDB();
                        newVirtualObjectForDB.setId(id);
                        newVirtualObjectForDB.setFunctionalityId(function.getFunction());
                        newVirtualObjectForDB.setAspectId(aspect.getAspect());
                        newVirtualObjectForDB.setName(aspect.getLabel() + function.getLabel());
                        virtualObjectDao.create(newVirtualObjectForDB);
                    }
                } catch (Exception e) {
                    log.error("ERROR Create VO {}, {}", function, aspect);
                }
            }
        }
    }
}
