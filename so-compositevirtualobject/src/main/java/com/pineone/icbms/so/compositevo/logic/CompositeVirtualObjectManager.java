package com.pineone.icbms.so.compositevo.logic;

import com.pineone.icbms.so.compositevo.entity.CompositeVirtualObject;

import java.util.List;

public interface CompositeVirtualObjectManager {
    CompositeVirtualObject searchCompositeVO(String id);
    void updateCompositeVO(CompositeVirtualObject compositeVirtualObject);
    void deleteCompositeVO(String id);
    void executeCompositeVO(String cvoId, String domain, String functionality, String operation, String sessionId);
    List<CompositeVirtualObject> searchCompositeVOList();
    List<CompositeVirtualObject> searchLocationCompositeVO(String location);
    void createCompositeVO(CompositeVirtualObject compositeVirtualObject);
}
