package com.pineone.icbms.so.interfaces.database.model.key;

import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by melvin on 2017. 5. 16..
 */

@Service
public class DeviceControlPK implements Serializable {
    //
    private String id;
    private String contextModelId;

    public DeviceControlPK(String id, String contextModelId) {
        this.id = id;
        this.contextModelId = contextModelId;
    }

    public DeviceControlPK() {
    }

    public boolean equals(Object object){
        if(object instanceof DeviceControlPK) {
            DeviceControlPK pk = (DeviceControlPK) object;
            return id.equals(pk.id) && contextModelId == pk.contextModelId;
        }
        else{
            return false;
        }
    }

    public String hascode(){
        return id.hashCode() + contextModelId;
    }




//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        DeviceControlPK that = (DeviceControlPK) o;
//
//        if (id != null ? !id.equals(that.id) : that.id != null) return false;
//        return contextModelId != null ? contextModelId.equals(that.contextModelId) : that.contextModelId == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (contextModelId != null ? contextModelId.hashCode() : 0);
//        return result;
//    }

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
}
