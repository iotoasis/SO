package com.pineone.icbms.so.interfaces.database.ref;

import org.springframework.stereotype.Service;

/**
 * Created by melvin on 2016. 8. 9..
 */

//  중요데이터의 누락이 있는지 검증
@Service
public class DataValidation {

    //  virtualObjectData Validation
//    public void inspectVirtualObjectData(VirtualObjectData virtualObjectData) throws DataLossException {
//        //
//        if (virtualObjectData.getName() == null
//                || virtualObjectData.getFunction_id() == null
//                || virtualObjectData.getAspect_id() == null
//                || virtualObjectData.getDescription() == null) {
//            throw new DataLossException();
//        }
//    }
//
//    public void inspectAspectData(AspectData aspectData) throws DataLossException {
//        //
//        if (aspectData.getName() == null
//                || aspectData.getUri() == null
//                || aspectData.getDescription() == null) {
//            throw new DataLossException();
//        }
//    }
}
