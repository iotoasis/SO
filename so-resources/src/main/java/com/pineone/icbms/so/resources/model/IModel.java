package com.pineone.icbms.so.resources.model;

import com.pineone.icbms.so.resources.property.IIdOwner;
import com.pineone.icbms.so.resources.property.INameOwner;

/**
 * model interface.<BR/>
 * Created by uni4love on 2015. 10. 15..
 */
public interface IModel<MODEL_TYPE, ID, NAME, DATE> extends IIdOwner<ID>, INameOwner<NAME>
{
    /**
     * return id
     * @return id
     */
    ID getId();

    /**
     * return name
     * @return name
     */
    NAME getName();

    /**
     * return type
     * @return type
     */
    MODEL_TYPE getType();

    /**
     * return created date.<BR/>
     * @return date
     */
    DATE getCreatedDate();

    /**
     * return modified date.<BR/>
     * @return date
     */
    DATE getModifiedDate();

    /**
     * return description.<BR/>
     *
     * @return description
     */
    String getDescription();

    /**
     * return status.<BR/>
     * @return status
     */
    String getStatus();
}
