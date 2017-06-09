package com.pineone.icbms.so.interfaces.database.logic.itf;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.TypeOfCompositeVirtualObjectData;
import com.pineone.icbms.so.interfaces.database.model.TypeOfCompositeVirtualObjectForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
public interface ITypeOfCompositeVirtualObjectDAO {
    //
    TypeOfCompositeVirtualObjectForDB retrieveTypeOfCompositeVirtualObject(String id);

    List<TypeOfCompositeVirtualObjectForDB> retrieveTypeOfCompositeVirtualObjectList();

    String createTypeOfCompositeVirtualObject(TypeOfCompositeVirtualObjectData typeOfCompositeVirtualObjectData);

    String updateTypeOfCompositeVirtualObject(String id, TypeOfCompositeVirtualObjectData typeOfCompositeVirtualObjectData);

    String deleteTypeOfCompositeVirtualObject(String id);
}
