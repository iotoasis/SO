package com.pineone.icbms.so.iot.resources.model.repo.occurrence;

import java.util.List;

import com.pineone.icbms.so.resources.domain.DefaultDomain;
import com.pineone.icbms.so.resources.model.IGenericModel;

/**
 * Occurrence model interface.<BR/>
 * Created by uni4love on 2015. 12. 7..
 */
public interface IOccurrenceModel extends IGenericModel
{
	/**
	 * type: occurrence
	 */
	String TYPE = "so/resource/occurrence";

	/**
	 * return context model id.<BR/>
	 *
	 * @return context model id
	 */
	String getContextId();

	/**
	 * set context id.<BR/>
	 * 
	 * @param contextId
	 *            context id
	 */
	void setContextId(String contextId);

	/**
	 * return domain list.<BR/>
	 *
	 * @return domain list
	 */
	List<DefaultDomain> getDomainList();

	/**
	 * set domain list.<BR/>
	 * 
	 * @param domainList
	 *            domain list
	 */
	void setDomainList(List<DefaultDomain> domainList);

	/**
	 * return command.<BR/>
	 *
	 * @return command
	 */
	String getCmd();

	/**
	 * set cmd.<BR/>
	 * 
	 * @param cmd
	 *            cmd
	 */
	void setCmd(String cmd);

	/**
	 * return time.<BR/>
	 *
	 * @return time
	 */
	String getTime();

	/**
	 * set time.<BR/>
	 * 
	 * @param time
	 *            time
	 */
	void setTime(String time);
}
