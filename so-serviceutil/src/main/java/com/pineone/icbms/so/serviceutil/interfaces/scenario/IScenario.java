package com.pineone.icbms.so.serviceutil.interfaces.scenario;

import com.pineone.icbms.so.virtualobject.common.IGenericIdNameOwner;

import java.util.List;

/**
 * scenario interface.<BR/>
 *
 * Created by uni4love on 2017. 1. 12..
 */
public interface IScenario extends IGenericIdNameOwner {
    /**
     * return a ScenarioContext.<BR/>
     *
     * @return ScenarioContext
     */
    IScenarioContext getScenarioContext();

    /**
     * return scene list.<BR/>
     *
     * @return List<IScene>
     */
    List<IScene> getSceneList();
}
