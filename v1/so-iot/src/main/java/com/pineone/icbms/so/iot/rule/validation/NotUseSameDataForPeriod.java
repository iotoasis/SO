package com.pineone.icbms.so.iot.rule.validation;

/**
 * if use Data is duplicated, Occur This Exception<BR/>
 * Created by Melvin on 2016. 1. 26..
 */
public class NotUseSameDataForPeriod extends Exception {

    public NotUseSameDataForPeriod() {
        super("Not Use Same Data Within a Fixed Period");
    }

    public NotUseSameDataForPeriod(String message){
        super(message);
    }
}
