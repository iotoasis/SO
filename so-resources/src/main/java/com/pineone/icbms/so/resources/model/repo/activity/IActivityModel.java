package com.pineone.icbms.so.resources.model.repo.activity;

import com.pineone.icbms.so.resources.model.IModel;
import com.pineone.icbms.so.resources.model.repo.action.IActionModel;

import java.util.List;

/**
 * DefaultActivity model interface for repository.<BR/>
 * Created by uni4love on 2015. 10. 15..
 */
public interface IActivityModel extends IModel
{
    /**
     * return Action model list.<BR/>
     * @return action model list
     */
    List<IActionModel> getActionModelList();
}
