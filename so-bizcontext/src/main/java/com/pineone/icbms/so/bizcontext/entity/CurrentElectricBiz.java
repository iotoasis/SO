package com.pineone.icbms.so.bizcontext.entity;

/**
 * Created by melvin on 2016. 8. 3..
 */
public class CurrentElectricBiz implements BizContext {
    //
    String id;
    String name;
    int value;
    int compareValue;
    boolean result;

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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCompareValue() {
        return compareValue;
    }

    public void setCompareValue(int compareValue) {
        this.compareValue = compareValue;
    }
    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
