package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.OrchestrationServiceForDB;
import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import com.pineone.icbms.so.util.id.IdUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
@Component
public class OrchestrationServiceDao extends AbstractDao {
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

    // retrieve one
    public OrchestrationServiceForDB retrieve(String id) {
        OrchestrationServiceForDB osDB = super.sqlSession.selectOne("retrieveOrchestrationServiceById", id);
        osDB.setCompositeVirtualObjectForDBList(retrieveCvo(id));
        List<VirtualObjectForDB> voList = retrieveVo(id);
        osDB.setVirtualObjectForDBList(voList);
        return osDB;//super.sqlSession.selectOne("retrieveOrchestrationServiceById", id);
    }

    // retrieve list
    public List<OrchestrationServiceForDB> retrieve(OrchestrationServiceForDB model) {
        return super.sqlSession.selectList("retrieveOrchestrationServiceByModel", model);
    }

    // retrieve all
    public List<OrchestrationServiceForDB> retrieve() {
        return super.sqlSession.selectList("retrieveOrchestrationServiceByModel");
    }

    // retrieve list
    public List<CompositeVirtualObjectForDB> retrieveCvo(String osid) {
        return super.sqlSession.selectList("retrieveCvoInOrchestrationService", osid);
    }

    // retrieve list
    public List<VirtualObjectForDB> retrieveVo(String osid) {
        return super.sqlSession.selectList("retrieveVoInOrchestrationService", osid);
    }

    // 저장 기능 구현
    public OrchestrationServiceForDB create(OrchestrationServiceForDB model) {
        String sessionId = IdUtils.createRandomUUID();
        model.setId("OS-" + sessionId);
        super.sqlSession.insert("createOrchestrationService", model);
        // os_cvo
        if (model.getCompositeVirtualObjectIds() != null && model.getCompositeVirtualObjectIds().size() != 0) {
            List<CompositeVirtualObjectForDB> cvoDBList = new ArrayList<CompositeVirtualObjectForDB>();
            for (String id : model.getCompositeVirtualObjectIds()) {
                CompositeVirtualObjectForDB db = new CompositeVirtualObjectForDB();
                db.setId(id);
                cvoDBList.add(db);
            }
            model.setCompositeVirtualObjectForDBList(cvoDBList);
            super.sqlSession.insert("createOsCvo", model);
        }
        // os_vo
        if (model.getVirtualObjectIds() != null && model.getVirtualObjectIds().size() != 0) {
            List<VirtualObjectForDB> voDBList = new ArrayList<VirtualObjectForDB>();
            for (String id : model.getVirtualObjectIds()) {
                VirtualObjectForDB db = new VirtualObjectForDB();
                db.setId(id);
                voDBList.add(db);
            }
            model.setVirtualObjectForDBList(voDBList);
            super.sqlSession.insert("createOsVo", model);
        }
        return model;//super.sqlSession.selectOne("retrieveOrchestrationServiceById", model.getId());
    }

    // 갱신 기능 구현
    public int update(OrchestrationServiceForDB model) {
        //
        return super.sqlSession.update("updateOrchestrationService", model);
    }

    // 삭제 기능 구현
    public int delete(String id) {
        super.sqlSession.delete("deleteOsCvo", id);
        super.sqlSession.delete("deleteOsVo", id);
        return super.sqlSession.delete("deleteOrchestrationService", id);
    }
}
