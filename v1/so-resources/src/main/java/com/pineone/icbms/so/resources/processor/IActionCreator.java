package com.pineone.icbms.so.resources.processor;

import com.pineone.icbms.so.resources.action.IAction;
import com.pineone.icbms.so.resources.model.repo.action.IActionModel;

import java.util.List;

/**
 * Created by existmaster on 2016. 1. 8..
 */
public interface IActionCreator<ACTION, ACTION_MODEL> {

    /**
     * Create Actions
     * @param actionModelList actionModelList
     * @return List of ACTION
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    List<ACTION> createActions(List<ACTION_MODEL> actionModelList) throws ClassNotFoundException, IllegalAccessException, InstantiationException;;

    /**
     * This Method is return IAction Object when input ActionModel.
     * @param actionModel is Action description.
     * @return IAction is new instance of packageName from actionModel.
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    ACTION createAction(ACTION_MODEL actionModel) throws ClassNotFoundException, IllegalAccessException, InstantiationException;

}
