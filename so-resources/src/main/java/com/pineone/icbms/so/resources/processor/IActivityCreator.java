package com.pineone.icbms.so.resources.processor;

import com.pineone.icbms.so.resources.activity.IGenericActivity;
import com.pineone.icbms.so.resources.model.repo.activity.DefaultActivityModel;

import java.util.List;

/**
 * Created by existmaster on 2016. 1. 11..
 */
public interface IActivityCreator<ACTIVITY, ACTIVITY_MODEL, CONTEXT> {
    /**
     * Create Activities.
     * @param activityModelList activityModelList
     * @return List of ACTIVITY
     */
    List<ACTIVITY> createActivities(List<ACTIVITY_MODEL> activityModelList, CONTEXT context);

    /**
     * Create Activity.
     * @param activityModel activityModel
     * @return ACTIVITY
     */
    ACTIVITY createActivity(ACTIVITY_MODEL activityModel, CONTEXT context);
}
