package com.pineone.icbms.so.service.proxy;

public class DataServiceObject {

    public static final int	DATASERIVCE_CMID        	= 0;
    public static final int	DATASERIVCE_CMID_PARAM1 	= 1;
    public static final int	DATASERIVCE_CMID_PARAM2 	= 2;
    public static final int	DATASERIVCE_CMID_PARAM3 	= 3;
    public static final int	DATASERIVCE_CMID_PARAM4 	= 4;

    private String cmId;
    private String param1;
    private String param2;
    private String param3;
    private String param4;

    public DataServiceObject() {
    }

    public DataServiceObject(String cmId, String param1, String param2, String param3, String param4) {
        this.cmId = cmId;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
    }

    public String getCmId() {
        return cmId;
    }

    public void setCmId(String cmId) {
        this.cmId = cmId;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getParam4() {
        return param4;
    }

    public void setParam4(String param4) {
        this.param4 = param4;
    }

    public int getParam(){
        int paramCount = 0;

        if(param4 != null && !param4.isEmpty()){
            paramCount = 4;
        } else if(param3 != null && !param3.isEmpty()){
            paramCount = 3;
        } else if(param2 != null && !param2.isEmpty()){
            paramCount = 2;
        } else if(param1 != null && !param1.isEmpty()){
            paramCount = 1;
        }

            return paramCount;
    }


    @Override
    public String toString() {
        return "DataServiceObject{" +
                "cmId='" + cmId + '\'' +
                ", param1='" + param1 + '\'' +
                ", param2='" + param2 + '\'' +
                ", param3='" + param3 + '\'' +
                ", param4='" + param4 + '\'' +
                '}';
    }

}
