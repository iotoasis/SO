package com.pineone.icbms.so.service.ref;

import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.util.exception.DataLossException;

/**
 * Created by melvin on 2016. 8. 9..
 */
public class DataValidation {

    public static DataValidation newDataValidation(){
        DataValidation dataValidation = new DataValidation();
        return dataValidation;
    }

    //NOTE : Service 데이터 검증
    public void inspectService(Service service) throws DataLossException {
        //
        if(service.getName() == null || service.getId() == null || service.getDeviceObjectId() == null
                || service.getConceptServiceId() == null || service.getStatus() == null){
            throw new DataLossException();
        }
    }

}
