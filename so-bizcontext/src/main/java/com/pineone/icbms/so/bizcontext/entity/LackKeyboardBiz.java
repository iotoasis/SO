package com.pineone.icbms.so.bizcontext.entity;

/**
 * Created by melvin on 2016. 8. 3..
 */
public class LackKeyboardBiz implements BizContext {
    //
    String id;
    String name;
    int currentQuantity;
    int nextLectureNeedQuantity;
    int lackQuantity;

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

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }
}
