package com.pineone.icbms.so.util.session.store.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by melvin on 2016. 10. 7..
 */
//NOTE : 디비에 저장하기 위한 형태의 session Data
@Document(collection = "session")
public class SessionDataObject {

    String id;
    String profileId;
    String priority;

    public SessionDataObject() {
    }

    public SessionDataObject(String id, String profileId, String priority) {
        this.id = id;
        this.profileId = profileId;
        this.priority = priority;
    }


    public SessionDataObject(String id) {
        this.id = id;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
