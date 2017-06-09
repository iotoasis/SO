package com.pineone.icbms.so.interfaces.database.logic.itf;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.VirtualObjectData;
import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 27..
 */
public interface IVirtualObjectDAO {
    //
    VirtualObjectForDB retrieveVirtualObject(String id);

    List<VirtualObjectForDB> retrieveVirtualObjectList();

    VirtualObjectForDB createVirtualObject(VirtualObjectData virtualObjectData);

    VirtualObjectForDB updateVirtualObject(String id, VirtualObjectData virtualObjectData);

    String deleteVirtualObject(String id);
}
