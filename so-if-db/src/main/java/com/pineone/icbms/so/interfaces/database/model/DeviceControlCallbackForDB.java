package com.pineone.icbms.so.interfaces.database.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DeviceControlCallbackForDB model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 13..
 */
@ToString
public class DeviceControlCallbackForDB extends CommonEntity {

    @Getter @Setter
    String parentId;

    @Getter @Setter
    String virtualObjectId;

}

