package com.pineone.icbms.so.interfaces.database.logic.itf;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.FunctionalityData;
import com.pineone.icbms.so.interfaces.database.model.FunctionalityForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
public interface IFunctionalityDAO {
    //
    FunctionalityForDB retrieveFunctionality(String id);

    List<FunctionalityForDB> retrieveFunctionalityList();

    FunctionalityForDB createFunctionality(FunctionalityData functionalityData);

    FunctionalityForDB updateFunctionality(String id, FunctionalityData functionalityData);

    String deleteFunctionality(String id);
}
