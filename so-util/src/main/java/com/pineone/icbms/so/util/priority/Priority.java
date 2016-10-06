package com.pineone.icbms.so.util.priority;

/**
 * Created by melvin on 2016. 10. 6..
 */
public enum Priority {
    EMERGENCY (1),
    IMMEDEATELY (2),
    SCHEDULE(3);

    int priorityNum;

    Priority(int priorityNum) {
        this.priorityNum = priorityNum;
    }

    public boolean isHigherPriority(Priority currentData , Priority pastData){
        boolean inspector = false;
        if(currentData.priorityNum < pastData.priorityNum){
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
