package com.pineone.icbms.so.interfaces.database.model.mapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by melvin on 2017. 5. 11..
 */

@Entity
@Table(name = "CM_CI")
public class CM_CI_MapperForDB {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "context_model_id")
    private String contextModelId;

    @Column(name = "context_information_id")
    private String contextInformationId;

    public CM_CI_MapperForDB() {
    }

    public CM_CI_MapperForDB(String contextModelId, String contextInformationId) {
        this.contextModelId = contextModelId;
        this.contextInformationId = contextInformationId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContextModelId() {
        return contextModelId;
    }

    public void setContextModelId(String contextModelId) {
        this.contextModelId = contextModelId;
    }

    public String getContextInformationId() {
        return contextInformationId;
    }

    public void setContextInformationId(String contextInformationId) {
        this.contextInformationId = contextInformationId;
    }

    @Override
    public String toString() {
        return "CM_CI_MapperForDB{" +
                "id='" + id + '\'' +
                ", contextModelId='" + contextModelId + '\'' +
                ", contextInformationId='" + contextInformationId + '\'' +
                '}';
    }
}
