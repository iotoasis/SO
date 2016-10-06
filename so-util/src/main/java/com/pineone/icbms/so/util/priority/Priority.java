package com.pineone.icbms.so.util.priority;

/**
 * Created by melvin on 2016. 10. 6..
 */
public enum Priority {
    HIGH (1),
    MEDIUM (2),
    LOW (3);

    int priorityNum;

    Priority(int priorityNum) {
        this.priorityNum = priorityNum;
    }

    public boolean isHigherPriority(Priority priority){
        boolean inspector = false;
        if(this.priorityNum > priority.priorityNum){
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
