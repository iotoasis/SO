package com.pineone.icbms.so.iot.resources.occurence;

import com.pineone.icbms.so.resources.domain.IDomain;

import java.util.List;

/**
 * Generic occurence interface.<BR/>
 * Created by uni4love on 2015. 10. 20..
 */
public interface IGenericOccurence extends IOccurence
{
    /**
     * return context model id.<BR/>
     * @return context model id
     */
    String getContextModelId();

    /**
     * return domain list.<BR/>
     * @return domain list
     */
    List<IDomain> getDomainList();
}
