package com.pineone.icbms.so.interfaces.database.logic.itf;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.OrchestrationServiceData;
import com.pineone.icbms.so.interfaces.database.model.OrchestrationServiceForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
public interface IOrchestrationServiceDAO {
    //
    OrchestrationServiceForDB retrieveOrchestrationService(String id);

    List<OrchestrationServiceForDB> retrieveOrchestrationServiceList();

    OrchestrationServiceForDB createOrchestrationService(OrchestrationServiceData orchestrationServiceData);

    OrchestrationServiceForDB updateOrchestrationService(String id, OrchestrationServiceData orchestrationServiceData);

    String deleteOrchestrationService(String id);
}
