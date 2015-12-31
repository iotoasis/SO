package com.pineone.icbms.so.resources.vo;


import com.pineone.icbms.so.resources.property.Condition;
import com.pineone.icbms.so.resources.task.DefaultTask;

import java.io.Serializable;
import java.util.List;

/**
 * Created by existmaster on 2015. 10. 30..
 */
public class VirtualObject implements Serializable {

    private String id;

    private List<Condition> conditions;
    private List<DefaultTask> tasks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<DefaultTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<DefaultTask> tasks) {
        this.tasks = tasks;
    }

    public VirtualObject() {
    }

    public VirtualObject(String id) {
        this.id = id;
    }
}
