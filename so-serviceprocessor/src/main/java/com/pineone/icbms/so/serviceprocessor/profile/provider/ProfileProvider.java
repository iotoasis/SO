package com.pineone.icbms.so.serviceprocessor.profile.provider;

import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import com.pineone.icbms.so.virtualobject.profile.IGenericProfile;
import com.pineone.icbms.so.serviceutil.interfaces.database.DatabaseManager;
import com.pineone.icbms.so.serviceutil.interfaces.provider.IProfileProvider;

import java.util.List;

/**
 * Profile provider implements.<BR/>
 *
 * Created by uni4love on 2017. 1. 4..
 */
public class ProfileProvider implements IProfileProvider {
    /**
     * Create
     *
     * @param iGenericProfile
     * @return ID
     */
    @Override
    public String create(IGenericProfile iGenericProfile) {
        return null;
    }

    /**
     * Retreive
     *
     * @param s
     * @return M
     */
    @Override
    public IGenericProfile retreive(String s) {
        return null;
    }

    /**
     * Update
     *
     * @param iGenericProfile
     * @return model ID
     */
    @Override
    public String update(IGenericProfile iGenericProfile) {
        return null;
    }

    /**
     * Delete
     *
     * @param iGenericProfile
     * @return model ID
     */
    @Override
    public String delete(IGenericProfile iGenericProfile) {
        return null;
    }

    /**
     * return ProfileForDB from database.<BR/>
     *
     * @param contextModelSid context model id
     * @param locationUri location uri
     * @return ProfileForDB
     */
    public List<ProfileForDB> getProfileListByContextModelSidAndLocationUri(String contextModelSid, String locationUri) {
        return DatabaseManager.getInstance().getProfileListByContextModelSidAndLocationUri(contextModelSid, locationUri);
    }
}
