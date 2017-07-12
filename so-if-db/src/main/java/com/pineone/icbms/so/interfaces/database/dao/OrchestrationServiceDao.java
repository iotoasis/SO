package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.OrchestrationServiceForDB;
import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
@Component
public class OrchestrationServiceDao extends AbstractDao {
//    @Autowired
//    private CompositeVirtualObjectDao cvoDao;
//    @Autowired
//    private VirtualObjectDao voDao;

    //
    public OrchestrationServiceForDB retrieveOrchestrationService(String id) {
        //
//        OrchestrationServiceForDB orchestrationServiceForDB = orchestrationServiceRepository.findOne(id);
//        List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList = dataBaseStore.getCompositeVirtualObjectListByOrchestrationId(id);
//        List<VirtualObjectForDB> virtualObjectForDBList = dataBaseStore.getVirtualObjectListByOrchestrationId(id);
//        orchestrationServiceForDB.setCompositeVirtualObjectForDBList(compositeVirtualObjectForDBList);
//        orchestrationServiceForDB.setVirtualObjectForDBList(virtualObjectForDBList);
//        return orchestrationServiceForDB;

        // get orchestrationService
        OrchestrationServiceForDB orchestrationServiceForDB = super.sqlSession.selectOne("retrieveOrchestrationServiceById", id);

//        // get cvo
//        List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList = cvoDao.getCompositeVirtualObjectListByOrchestrationId(id);
//
//        // get vo
//        List<VirtualObjectForDB> virtualObjectForDBList = voDao.getVirtualObjectListByOrchestrationId(id);
//
//        orchestrationServiceForDB.setCompositeVirtualObjectForDBList(compositeVirtualObjectForDBList);
//        orchestrationServiceForDB.setVirtualObjectForDBList(virtualObjectForDBList);

        return orchestrationServiceForDB;
        //return super.sqlSession.selectOne("getOrchestrationServiceById", id);
    }

    public List<OrchestrationServiceForDB> retrieveOrchestrationServiceList() {
        return null;
    }

//    public OrchestrationServiceForDB createOrchestrationService(OrchestrationServiceData orchestrationServiceData) {
//        return null;
//    }
//
//    public OrchestrationServiceForDB updateOrchestrationService(String id, OrchestrationServiceData orchestrationServiceData) {
//        return null;
//    }

    public String deleteOrchestrationService(String id) {
        return null;
    }
}
