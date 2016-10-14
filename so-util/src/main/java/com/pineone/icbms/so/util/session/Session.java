package com.pineone.icbms.so.util.session;

import java.util.Date;
import java.util.Map;

/**
 * Created by existmaster on 2016. 10. 5..
 */
public interface Session {

    String getId();

    boolean isExistSessionData(String key);

    String findSessionData(String key);

    String insertSessionData(String key, String value);

    String removeSessionData(String key);

    Map<String, String> getSessionData();

    String getCreateDate();

    void setCreateDate(String createDate);

    Date getMongoTime();

    void setMongoTime(Date time);

    Long getCalculateTime();

    void setCalculateTime(Long calculateTime);


}
