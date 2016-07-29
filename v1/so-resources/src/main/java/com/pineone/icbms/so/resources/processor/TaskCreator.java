package com.pineone.icbms.so.resources.processor;


import java.util.ArrayList;

import com.pineone.icbms.so.resources.activity.DefaultActivity;
import com.pineone.icbms.so.resources.activity.IActivity;
import com.pineone.icbms.so.resources.task.DefaultTask;

/**
 * Created by Melvin on 2015. 11. 16..
 * Make DefaultTask Using Activities instance(IActivity Interface)
 */

public class TaskCreator {


    ActivityCreator activityCreator = new ActivityCreator();

    public DefaultTask makeTask(ArrayList<DefaultActivity> activitiesName){

        DefaultTask task = new DefaultTask();

        for (DefaultActivity getDefaultActivity : activitiesName) {

//            long activityId = getDefaultActivity.getId();
//
//            IActivity activity = activityCreator.connectActivityAndAction(activityId);
//
//            task.addActivities(activity);
        }

        return task;
    }
}
