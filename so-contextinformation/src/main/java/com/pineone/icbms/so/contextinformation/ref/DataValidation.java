package com.pineone.icbms.so.contextinformation.ref;

import com.pineone.icbms.so.contextinformation.entity.ContextInformation;
import com.pineone.icbms.so.util.exception.DataLossException;

/**
 * Created by melvin on 2016. 8. 9..
 */
public class DataValidation {

    public static DataValidation newDataValidation(){
        DataValidation dataValidation = new DataValidation();
        return dataValidation;
    }

    //NOTE : ContextInformationLogic 데이터 검증
    public void inspectGeneralContext(ContextInformation contextInformation) throws DataLossException {
        //
        if(contextInformation.getId() == null || contextInformation.getName() == null ||
                contextInformation.getConceptService() == null || contextInformation.getDeviceObject() == null
                || (contextInformation.getMaxValue() == 0 && contextInformation.getMinValue() == 0)){
            throw new DataLossException();
        }
    }

}
