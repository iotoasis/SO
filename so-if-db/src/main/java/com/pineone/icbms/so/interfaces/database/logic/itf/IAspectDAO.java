package com.pineone.icbms.so.interfaces.database.logic.itf;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.AspectData;
import com.pineone.icbms.so.interfaces.database.model.AspectForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 28..
 */
public interface IAspectDAO {
    //
    AspectForDB retrieveAspect(String id);

    List<AspectForDB> retrieveAspectList();

    AspectForDB createAspect(AspectData aspectData);

    AspectForDB updateAspect(String id, AspectData AspectData);

    String deleteAspect(String id);

    // virtualObject 에 포함되어 있는 Aspect 조회
    AspectForDB retrieveAspectByVirtualObjectId(String virtualObjectId);

}
