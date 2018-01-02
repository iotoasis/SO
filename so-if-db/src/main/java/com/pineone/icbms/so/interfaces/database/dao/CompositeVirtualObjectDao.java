package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.NonDeviceCvoForDB;
import com.pineone.icbms.so.interfaces.database.model.RuleBodyForDB;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
@Component
public class CompositeVirtualObjectDao extends AbstractDao {
    //
    // os 와 연결된 cvo list
    public List<CompositeVirtualObjectForDB> retrieveCompositeVirtualObjectListByOrchestrationId(String orchestrationServiceId) {
        return super.sqlSession.selectList("retrieveCompositeVirtualObjectListByOrchestrationId", orchestrationServiceId);
    }

    //Rule_body값 읽어오기 (osId로 부터)
	public List<RuleBodyForDB> retrieveRuleBodyListByOsId(String osId) {
        return super.sqlSession.selectList("retrieveRuleBodyListByOsId", osId);
	}

    //NonCVO에 연결된 RuleBody값 읽어오기 (nCvoId, osId로 부터)
	public List<NonDeviceCvoForDB> retrieveNonDeviceCvoList(String nCvoId, String osId) {
		HashMap<String, String> param = new HashMap<>();
		param.put("nonCvoId", nCvoId);
		param.put("osId", osId);
        return super.sqlSession.selectList("retrieveNonDeviceCvoList", param);
	}

    // retrieve one
    public CompositeVirtualObjectForDB retrieve(String id) {
        return super.sqlSession.selectOne("retrieveCompositeVirtualObjectById", id);
    }

    // retrieve list
    public List<CompositeVirtualObjectForDB> retrieve(CompositeVirtualObjectForDB model) {
        return super.sqlSession.selectList("retrieveCompositeVirtualObjectByModel", model);
    }

    // retrieve all
    public List<CompositeVirtualObjectForDB> retrieve() {
        return super.sqlSession.selectList("retrieveCompositeVirtualObjectByModel");
    }

    // 저장 기능 구현
    public CompositeVirtualObjectForDB create(CompositeVirtualObjectForDB model) {
        //String sessionId = IdUtils.createRandomUUID();
        //model.setId("CVO-" + sessionId);
        super.sqlSession.insert("createCompositeVirtualObject", model);
        return super.sqlSession.selectOne("retrieveCompositeVirtualObjectById", model.getId());
    }

    // 갱신 기능 구현
    public CompositeVirtualObjectForDB update(CompositeVirtualObjectForDB model) {
        //
        super.sqlSession.update("updateCompositeVirtualObject", model);
        return super.sqlSession.selectOne("retrieveCompositeVirtualObjectByModel", model);
    }

    // 삭제 기능 구현
    public int delete(String id) {
        return super.sqlSession.delete("deleteCompositeVirtualObject", id);
    }

}
