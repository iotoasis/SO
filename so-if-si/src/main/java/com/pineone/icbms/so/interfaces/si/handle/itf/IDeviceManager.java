package com.pineone.icbms.so.interfaces.si.handle.itf;

import com.pineone.icbms.so.interfaces.si.model.ResultMessage;

/**
 * Created by melvin on 2017. 4. 4..
 */
public interface IDeviceManager {

    //디바이스 제어시 사용
    //ResultMessage deviceExecute(String commandId, String deviceId, String deviceCommand);
    ResultMessage deviceExecute(String commandId, String deviceId, String aspectId, String deviceCommand);
}
