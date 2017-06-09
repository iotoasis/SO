package com.pineone.icbms.so.virtualobject.common;

/**
 * ID, Name owner interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
public interface IIdNameOwner<ID, NAME> {
    /**
     * return id.<BR/>
     *
     * @return id
     */
    ID getId();

    /**
     * return name.<BR/>
     *
     * @return name
     */
    NAME getName();
}
