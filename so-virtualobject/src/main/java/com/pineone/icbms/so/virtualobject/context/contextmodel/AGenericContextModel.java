package com.pineone.icbms.so.virtualobject.context.contextmodel;

import com.pineone.icbms.so.virtualobject.common.AGenericServiceEntity;

/**
 * generic context model abstract class.<BR/>
 *
 * Created by uni4love on 2016. 11. 23..
 */
abstract public class AGenericContextModel extends AGenericServiceEntity implements IGenericContextModel {
    /**
     * uri
     */
    protected String uri;

    protected String resultCmValue;

    /**
     * construcotr
     */
    public AGenericContextModel() {
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     */
    public AGenericContextModel(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public AGenericContextModel(String id, String name) {
        this(id);
        this.name = name;
    }

    /**
     * constructor<BR/>
     *
     * @param id          id
     * @param name        name
     * @param description description
     */
    public AGenericContextModel(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }


    @Override
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getResultCmValue() {
		return resultCmValue;
	}

	public void setResultCmValue(String resultCmValue) {
		this.resultCmValue = resultCmValue;
	}

	@Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append(", uri: ").append(uri);
        return sb.toString();
    }
}
