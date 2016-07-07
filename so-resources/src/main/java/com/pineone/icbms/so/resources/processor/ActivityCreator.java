package com.pineone.icbms.so.resources.processor;


import com.pineone.icbms.so.resources.activity.IActivity;

/**
 * Created by Melvin on 2015. 11. 13..
 * Make DefaultActivity Using Package Path that Saved on DataBase , and Class Loader
 */
public class ActivityCreator {

    public static final String PACKAGE_PATH = "com.pineone.icbms.so.iot.service.activity.";

    public IActivity connectActivityAndAction(String activityId) {

        IActivity activity = null;
        try {
            activity = (IActivity) Class.forName(PACKAGE_PATH + activityId).newInstance();

//                activity.invokeAction();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return activity;
    }
}
