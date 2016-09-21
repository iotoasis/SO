package com.pineone.icbms.so.compositevo.logic;

import com.pineone.icbms.so.compositevo.entity.CompositeVirtualObject;
import com.pineone.icbms.so.compositevo.entity.VirtualObjectControlData;

import java.util.List;

public interface CompositeVirtualObjectManager {
    CompositeVirtualObject searchCompositeVO(String id);
    void updateCompositeVO(CompositeVirtualObject compositeVirtualObject);
    void deleteCompositeVO(String id);
    void executeCompositeVO(VirtualObjectControlData virtualObjectControlData);
    List<CompositeVirtualObject> searchCompositeVOList();
    List<CompositeVirtualObject> searchLocationCompositeVO(String location);
    void createCompositeVO(CompositeVirtualObject compositeVirtualObject);
}
