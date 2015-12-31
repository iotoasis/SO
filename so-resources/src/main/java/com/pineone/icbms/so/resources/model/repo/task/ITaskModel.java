package com.pineone.icbms.so.resources.model.repo.task;

import com.pineone.icbms.so.resources.model.IModel;
import com.pineone.icbms.so.resources.model.repo.activity.IActivityModel;

import java.util.List;

/**
 * Task model interface for repository.<BR/>
 * Created by uni4love on 2015. 10. 15..
 */
public interface ITaskModel extends IModel
{
    /**
     * return activity model list.<BR/>
     * @return activity model list
     */
    List<IActivityModel> getActivityModelList();
}
