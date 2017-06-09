package com.pineone.icbms.so.virtualobject.common;

/**
 * Identity interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
public interface IIdentity<ID, NAME, DESCRIPTION> extends IIdNameOwner<ID, NAME> {
    /**
     * return description.<BR/>
     *
     * @return description
     */
    DESCRIPTION getDescription();
}
