package com.pineone.icbms.so.iot.creator;

import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.action.IAction;
import com.pineone.icbms.so.resources.action.IGenericAction;
import com.pineone.icbms.so.resources.model.repo.action.DefaultActionModel;
import com.pineone.icbms.so.resources.model.repo.action.IActionModel;
import com.pineone.icbms.so.resources.processor.IActionCreator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by existmaster on 2016. 1. 8..
 */
public class DefaultActionCreator implements IActionCreator<IGenericAction, DefaultActionModel>{

    /**
     * createActions.
     * @param actionModelList actionModelList
     * @return List of IGenericAction
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Override
    public List<IGenericAction> createActions(List<DefaultActionModel> actionModelList) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<IGenericAction> actionList = new ArrayList<IGenericAction>();
        for(DefaultActionModel actionModel : actionModelList)
        {
            actionList.add(createAction(actionModel));
        }
        return actionList;
    }

    /**
     * This Method is return IAction Object when input ActionModel.
     * @param actionModel is Action description from Database.
     * @return IAction is new instance of packageName from actionModel.
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IGenericAction createAction(DefaultActionModel actionModel) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String packageName = actionModel.getPackageName();
        DefaultAction action = (DefaultAction) Class.forName(packageName).newInstance();
        action.setId(actionModel.getId()+"_"+System.nanoTime());
        action.setName(actionModel.getName());
        System.out.println(action.toString());
        return action;
    }
}
