package com.pineone.icbms.so.interfaces.si.handle.itf;

import java.util.List;

/**
 * Created by melvin on 2017. 4. 4..
 */
public interface IDeviceManager {

    //디바이스 제어시 사용
    String deviceExecute(String commandId, String deviceId,String deviceCommand);
}
