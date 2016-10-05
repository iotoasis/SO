package com.pineone.icbms.so.creator.generalobject;

/**
 * Created by melvin on 2016. 10. 5..
 */
public class GeneralObjectTransformData
{
    String voId;
    String cvoId;

    public String getVoId() {
        return voId;
    }

    public void setVoId(String voId) {
        this.voId = voId;
    }

    public String getCvoId() {
        return cvoId;
    }

    public void setCvoId(String cvoId) {
        this.cvoId = cvoId;
    }

    public GeneralObjectTransformData() {
    }

    public GeneralObjectTransformData(String voId, String cvoId) {
        this.voId = voId;
        this.cvoId = cvoId;
    }

    @Override
    public String toString() {
        return "GeneralObjectTransformData{" +
                "voId='" + voId + '\'' +
                ", cvoId='" + cvoId + '\'' +
                '}';
    }
}
