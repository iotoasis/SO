package com.pineone.icbms.so.bizcontext.ref;

import com.pineone.icbms.so.util.exception.DataLossException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by melvin on 2016. 8. 9..
 */

@Service
public class DataValidation {

    @Autowired
    DataLossException dataLossException;

    public static DataValidation newDataValidation(){
        DataValidation dataValidation = new DataValidation();
        return dataValidation;
    }

    //NOTE : BizContext 데이터 검증
    public void inspectBizContext(String bizContextName) throws DataLossException {
        //
        if(bizContextName == null){
            throw dataLossException;
        }
    }

}
