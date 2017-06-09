package com.pineone.icbms.so.interfaces.database.logic.itf;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.ContextInformationData;
import com.pineone.icbms.so.interfaces.database.model.ContextInformationForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
public interface IContextInformationDAO {
    //
    ContextInformationForDB retrieveContextInformation(String id);

    List<ContextInformationForDB> retrieveContextInformationList();

    ContextInformationForDB createContextInformation(ContextInformationData contextInformationData);

    ContextInformationForDB updateContextInformation(String id, ContextInformationData contextInformationData);

    String deleteContextInformation(String id);
}
