package com.pineone.icbms.so.interfaces.database.logic.itf;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.CompositeVirtualObjectData;
import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
public interface ICompositeVirtualObjectDAO {
    //
    CompositeVirtualObjectForDB retrieveCompositeVirtualObject(String id);

    List<CompositeVirtualObjectForDB> retrieveCompositeVirtualObjectList();

    CompositeVirtualObjectForDB createCompositeVirtualObject(CompositeVirtualObjectData compositeVirtualObjectData);

    CompositeVirtualObjectForDB updateCompositeVirtualObject(String id, CompositeVirtualObjectData compositeVirtualObjectData);

    String deleteCompositeVirtualObject(String id);
}
