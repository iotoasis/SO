package com.pineone.icbms.so.profile.ref;

import com.pineone.icbms.so.profile.entity.Profile;
import com.pineone.icbms.so.util.exception.DataLossException;

/**
 * Created by melvin on 2016. 8. 9..
 */
public class DataValidation {

    public static DataValidation newDataValidation(){
        DataValidation dataValidation = new DataValidation();
        return dataValidation;
    }

    //NOTE : Profile 데이터 검증
    public void inspectProfile(Profile profile) throws DataLossException {
        //
        if(profile.getName() == null || profile.getContextModelId() == null
                || profile.getServiceModelId() == null){
            throw new DataLossException();
        }
    }
}
