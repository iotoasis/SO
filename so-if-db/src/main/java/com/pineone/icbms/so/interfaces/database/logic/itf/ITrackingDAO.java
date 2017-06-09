package com.pineone.icbms.so.interfaces.database.logic.itf;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.TrackingData;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;

/**
 * Created by jonghee on 2017-06-02.
 */
public interface ITrackingDAO {

    TrackingEntity createTracking(TrackingData trackingData);
}
