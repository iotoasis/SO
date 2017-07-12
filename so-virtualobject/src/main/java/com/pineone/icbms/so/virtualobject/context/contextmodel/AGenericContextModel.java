package com.pineone.icbms.so.virtualobject.context.contextmodel;

import com.pineone.icbms.so.virtualobject.common.AGenericServiceEntity;
import com.pineone.icbms.so.virtualobject.context.contextinformation.IGenericContextInformation;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * context information list
     */
    protected List<IGenericContextInformation> contextInformationList = null;

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

    /**
     * return Context Information list.<BR/>
     *
     * @return Context Information list
     */
    @Override
    public List<IGenericContextInformation> getContextInformationList() {
        return contextInformationList;
    }

    public void setContextInformationList(List<IGenericContextInformation> contextInformationList) {
        this.contextInformationList = contextInformationList;
    }

    public void addContextInformation(IGenericContextInformation contextInformation) {
        if (this.contextInformationList == null)
            contextInformationList = new ArrayList<IGenericContextInformation>();
        contextInformationList.add(contextInformation);
    }

    @Override
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }



    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append(", uri: ").append(uri);
        sb.append(", ci list: ").append(contextInformationList);
        return sb.toString();
    }
}
