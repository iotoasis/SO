package com.pineone.icbms.so.context_model.logic;

import com.pineone.icbms.so.context_model.ref.ContextType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 */
public class ContextTypeLogicImpl implements ContextTypeLogic {

    public static ContextTypeLogicImpl newContextTypeLogic(){
        return new ContextTypeLogicImpl();
    }

    //NOTE : ContextType 조회 - Emergency or Schedule
    public List<ContextType> retrieveContextTypeList(){
        //
        List<ContextType> contextTypeArrayList = new ArrayList<>();
        for(ContextType contextType : ContextType.values()){
            contextTypeArrayList.add(contextType);
        }
        return contextTypeArrayList;
    }
}
