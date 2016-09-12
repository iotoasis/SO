package com.pineone.icbms.so.compositevo.logic;

import com.pineone.icbms.so.compositevo.proxy.CompositeVirtualObjectProxy;
import com.pineone.icbms.so.compositevo.store.CompositeVirtualObjectStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompositeVirtualObjectManagerLogic implements CompositeVirtualObjectManager{
    //
    @Autowired
    private CompositeVirtualObjectStore compositeVirtualObjectStore;

    @Autowired
    private CompositeVirtualObjectProxy compositeVirtualObjectProxy;

}
