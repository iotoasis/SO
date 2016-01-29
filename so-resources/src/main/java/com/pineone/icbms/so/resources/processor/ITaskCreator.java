package com.pineone.icbms.so.resources.processor;

import java.util.List;

/**
 * Created by existmaster on 2016. 1. 11..
 */
public interface ITaskCreator<TASK, TASK_MODEL, CONTEXT> {

    /**
     * Create Task
     *
     * @param taskModel
     * @return
     */
    TASK createTask(TASK_MODEL taskModel, CONTEXT context);

    /**
     * Create Tasks
     *
     * @param taskModelList
     * @return
     */
    List<TASK> createTasks(List<TASK_MODEL> taskModelList, CONTEXT context);

}
