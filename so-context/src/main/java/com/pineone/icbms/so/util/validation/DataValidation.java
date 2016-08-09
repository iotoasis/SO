package com.pineone.icbms.so.util.validation;

import com.pineone.icbms.so.bizcontext.ref.Biz_Note;
import com.pineone.icbms.so.contextinformation.entity.ContextInformation;
import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.util.exception.DataLossException;

/**
 * Created by melvin on 2016. 7. 13..
 * NOTE: 송수신 데이터 체크
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

    //NOTE : ContextModel 데이터 검증
    public void inspectContextModel(ContextModel contextModel) throws DataLossException {
        //
        if(contextModel.getName() == null || contextModel.getContextType() == null ||
                contextModel.getDomainList() == null || contextModel.getContextInformationList() == null){
            throw new DataLossException();
        }
    }

    //NOTE : BizContext 데이터 검증
    public void inspectBizContext(String bizContextName) throws DataLossException {
        //
        if(bizContextName == null){
            throw new DataLossException();
        }
    }
}


