package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pineone.icbms.so.interfaces.database.model.ContextInformationForDB;
import com.pineone.icbms.so.util.time.DateFormat;
import lombok.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Context Model model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 13..
 */
//@Data
//@EqualsAndHashCode(callSuper=true)
@JsonPropertyOrder({"id","name", "description"})
@ToString
public class ContextModelForDB extends CommonEntity {

    @Getter @Setter
    private String createdId;

    @Getter @Setter
    private String modifiedId;

    @Getter @Setter
    private List<ContextInformationForDB> contextInformationForDBList;

//    public List<ContextInformationForDB> getContextInformationForDBList() {
//        return contextInformationForDBList;
//    }
//
//    public void setContextInformationForDBList(List<ContextInformationForDB> contextInformationForDBList) {
//        this.contextInformationForDBList = contextInformationForDBList;
//    }

}
