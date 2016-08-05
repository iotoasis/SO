package com.pineone.icbms.so.bizcontext.entity;

/**
 * Created by melvin on 2016. 8. 3..
 */
public class LackKeyboardBiz {
    //
    String id;
    String name;
    int currentQuantity;
    int nextLectureNeedQuantity;
    int lackQuantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public int getNextLectureNeedQuantity() {
        return nextLectureNeedQuantity;
    }

    public void setNextLectureNeedQuantity(int nextLectureNeedQuantity) {
        this.nextLectureNeedQuantity = nextLectureNeedQuantity;
    }

    public int getLackQuantity() {
        return lackQuantity;
    }

    public void setLackQuantity(int lackQuantity) {
        this.lackQuantity = lackQuantity;
    }
}
