package com.pineone.icbms.so.iot.rule.validation;

/**
 * if contextModel Id doesn't Exist In RuleProcessor , Occur This Exception<BR/>
 * Created by Melvin on 2015. 12. 24..
 */
public class NotExistContextIdException extends Exception{

    public NotExistContextIdException() {
        super("NotExist ContextModelId Exception");
    }

    public NotExistContextIdException(String message){
        super(message);
    }

}
