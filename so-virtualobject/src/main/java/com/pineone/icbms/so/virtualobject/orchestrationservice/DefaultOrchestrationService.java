package com.pineone.icbms.so.virtualobject.orchestrationservice;

/**
 * Service default class.<BR/>
 *
 * Created by uni4love on 2015. 11. 20..
 */
public class DefaultOrchestrationService extends AGenericOrchestrationService {

    /**
     * constructor
     */
    public DefaultOrchestrationService() {
    }
    /**
     * constructor<BR/>
     *
     * @param id id
     * @param name name
     */
    public DefaultOrchestrationService(String id, String name) {
        super(id, name);
    }

//    @Override
//    protected Map createStore() {
//        return new ConcurrentHashMap<String, V>();
//    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[").append(super.toString()).append("]");
        return sb.toString();
    }
}
