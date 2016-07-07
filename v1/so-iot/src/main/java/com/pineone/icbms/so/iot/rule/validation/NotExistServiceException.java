package com.pineone.icbms.so.iot.rule.validation;

/**
 * if ServiceModel doesn't Exist In RuleProcessor , Occur This Exception<BR/>
 * Created by Melvin on 2015. 12. 28..
 */
public class NotExistServiceException extends Exception{

    public NotExistServiceException() {
        super("NotExist Service Model Exception");
    }

    public NotExistServiceException(String message){
        super.getMessage();
    }
}
