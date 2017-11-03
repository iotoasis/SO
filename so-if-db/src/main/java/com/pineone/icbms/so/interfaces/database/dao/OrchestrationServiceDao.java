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
    
    public List<OrchestrationServiceForDB> retrieveServiceByProfile(String id) {
        return super.sqlSession.selectList("retrieveServiceByProfile", id);
    }
    
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
        osDB.setCompositeVirtualObjectForDBList(retrieveCvoByOsId(id));
        //List<VirtualObjectForDB> voList = retrieveVoByOsId(id);
        //osDB.setVirtualObjectForDBList(voList);
        return osDB;//super.sqlSession.selectOne("retrieveOrchestrationServiceById", id);
    }

    //os list by profile_id
	public List<OrchestrationServiceForDB> retrieveByProfile(String profileId) {
        return super.sqlSession.selectList("retrieveOrchestrationServiceByProfile", profileId);
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
    public List<CompositeVirtualObjectForDB> retrieveCvoByOsId(String osid) {
        return super.sqlSession.selectList("retrieveCvoInOrchestrationService", osid);
    }
/*
    // retrieve list
    public List<VirtualObjectForDB> retrieveVoByOsId(String osid) {
        return super.sqlSession.selectList("retrieveVoInOrchestrationService", osid);
    }
*/
    
    public OrchestrationServiceForDB create(OrchestrationServiceForDB model) {
    	super.sqlSession.insert("createOrchestrationService", model);
    	return model;
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
