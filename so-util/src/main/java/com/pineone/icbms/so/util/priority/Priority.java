package com.pineone.icbms.so.util.priority;

/**
 * Created by melvin on 2016. 10. 6..
 */
public enum Priority {
    MAXIMUM (4),
    HIGH (3),
    MEDIUM (2),
    LOW (1),
    MINIMUM(0);

    int priorityNum;

    Priority(int priorityNum) {
        this.priorityNum = priorityNum;
    }

    public boolean isImportantPriority(Priority priority){
        boolean inspector = false;
        if(this.priorityNum < priority.priorityNum){
            inspector = true;
        }
        return inspector;
    }

    public int getPriorityNum() {
        return priorityNum;
    }

    public void setPriorityNum(int priorityNum) {
        this.priorityNum = priorityNum;
    }
}
