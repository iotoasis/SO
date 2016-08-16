package com.pineone.icbms.so.servicemodel.ref;

import com.pineone.icbms.so.servicemodel.entity.ServiceModel;
import com.pineone.icbms.so.util.exception.DataLossException;

/**
 * Created by melvin on 2016. 8. 9..
 */
public class DataValidation {

    public static DataValidation newDataValidation(){
        DataValidation dataValidation = new DataValidation();
        return dataValidation;
    }

    //NOTE : ServiceModel 데이터 검증
    public void inspectServiceModel(ServiceModel serviceModel) throws DataLossException {
        //
        if(serviceModel.getName() == null || serviceModel.getServiceNameList() == null){
            throw new DataLossException();
        }
    }

}
