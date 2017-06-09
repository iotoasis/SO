package com.pineone.icbms.so.serviceutil.interfaces.provider;

/**
 * provider interface.<BR/>
 *
 * Created by uni4love on 2017. 1. 5..
 */
public interface IProvider<MODEL, ID> {
    /**
     * Create
     *
     * @param model
     * @return ID
     */
    ID create(MODEL model);

    /**
     * Retreive
     *
     * @param id
     * @return M
     */
    MODEL retreive(ID id);

    /**
     * Update
     *
     * @param model
     * @return model ID
     */
    ID update(MODEL model);

    /**
     * Delete
     *
     * @param model
     * @return model ID
     */
    ID delete(MODEL model);

}
