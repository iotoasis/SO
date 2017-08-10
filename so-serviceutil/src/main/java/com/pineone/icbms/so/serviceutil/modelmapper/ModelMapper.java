package com.pineone.icbms.so.serviceutil.modelmapper;

import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;
import com.pineone.icbms.so.interfaces.database.model.*;
import com.pineone.icbms.so.interfaces.messagequeue.model.*;
import com.pineone.icbms.so.virtualobject.profile.IGenericProfile;
import com.pineone.icbms.so.virtualobject.profile.DefaultProfile;
import com.pineone.icbms.so.util.conversion.JsonMapper;
import com.pineone.icbms.so.virtualobject.IGenericVirtualObject;
import com.pineone.icbms.so.virtualobject.aspect.IGenericAspect;
import com.pineone.icbms.so.virtualobject.common.IGenericIdentity;
import com.pineone.icbms.so.virtualobject.composite.IGenericCompositeVirtualObject;
import com.pineone.icbms.so.virtualobject.context.contextinformation.IGenericContextInformation;
import com.pineone.icbms.so.virtualobject.context.contextmodel.DefaultContextModel;
import com.pineone.icbms.so.virtualobject.context.contextmodel.IGenericContextModel;
import com.pineone.icbms.so.virtualobject.function.IGenericFunction;
import com.pineone.icbms.so.virtualobject.location.DefaultLocation;
import com.pineone.icbms.so.virtualobject.location.IGenericLocation;
import com.pineone.icbms.so.virtualobject.orchestrationservice.DefaultOrchestrationService;
import com.pineone.icbms.so.virtualobject.orchestrationservice.IGenericOrchestrationService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Model object mapper.<BR/>
 * <p>
 * Created by uni4love on 2017. 4. 5..
 */
public class ModelMapper extends JsonMapper {

    /**
     * contextmodel mapper
     */
    private static ContextModelMapper contextModelMapper = new ContextModelMapper();

    /**
     * contextmodel mapper
     */
    private static ContextInformationMapper contextInformationMapper = new ContextInformationMapper();

    /**
     * orchestrationservice mapper
     */
    private static OrchestrationServiceMapper orchestrationServiceMapper = new OrchestrationServiceMapper();

    /**
     * virtualobject mapper
     */
    private static VirtualObjectMapper virtualObjectMapper = new VirtualObjectMapper();

    /**
     * composite virtual object mapper
     */
    private static CompositeVirtualObjectMapper compositeVirtualObjectMapper = new CompositeVirtualObjectMapper();

    /**
     * aspect mapper
     */
    private static AspectMapper aspectMapper = new AspectMapper();

    /**
     * function mapper
     */
    private static FunctionMapper functionMapper = new FunctionMapper();

    /**
     * virtual device mapper
     */
    private static VirtualDeviceMapper virtualDeviceMapper = new VirtualDeviceMapper();

    /**
     * convert List<ProfileForDB> to List<IGenericProfile>.<BR/>
     *
     * @param profileForDbList List<ProfileForDB>
     * @return List<IGenericProfile>
     */
    public static List<IGenericProfile> toProfileList(List<ProfileForDB> profileForDbList) {
        List<IGenericProfile> profileList = null;
        if(profileForDbList != null && profileForDbList.size() > 0) {
            profileList = new ArrayList<>();
            for(ProfileForDB profileForDB: profileForDbList) {
                IGenericProfile profile = toProfile(profileForDB);
                profileList.add(profile);
            }
        }
        return profileList;
    }

    /**
     * convert ProfileForDB to IGenericProfile.<BR/>
     *
     * @param profileForDB ProfileForDB
     * @return IGenericProfile
     */
    public static IGenericProfile toProfile(ProfileForDB profileForDB) {
        DefaultProfile defaultProfile = null;
        if (profileForDB != null) {
            //context model
            DefaultContextModel contextModel = new DefaultContextModel();
            contextModel.setId(profileForDB.getContextModelId());
            //orchestration service
            DefaultOrchestrationService orchestrationService = new DefaultOrchestrationService();
            orchestrationService.setId(profileForDB.getOrchestrationServiceId());
            //location
            DefaultLocation location = new DefaultLocation();
            location.setUri(profileForDB.getLocationUri());
            //profile
            defaultProfile = new DefaultProfile(profileForDB.getId(), profileForDB.getName());
            defaultProfile.setContextModel(contextModel);
            defaultProfile.setOrchestrationService(orchestrationService);
            defaultProfile.setLocation(location);
        }
        return defaultProfile;
    }

    /**
     * convert to ContextModel model from ContextModelForMQ.<BR/>
     *
     * @param contextModelForMQ
     * @return IGenericContextModel
     */
    public static IGenericContextModel toContextModel(ContextModelForMQ contextModelForMQ) {
        return contextModelMapper.toProcessorModelFromMq(contextModelForMQ);
    }

    /**
     * convert ContextModelForDB to IGenericContextModel.<BR/>
     *
     * @param contextModelForDB ContextModelForDB
     * @return IGenericContextModel
     */
    public static IGenericContextModel toContextModel(ContextModelForDB contextModelForDB) {
        return contextModelMapper.toProcessorModelFromDb(contextModelForDB);
    }

    /**
     * convert List<ContextInformationForDB> to List<IGenericContextInformation> <BR/>
     *
     * @param contextInformationForDBList List<ContextInformationForDB>
     * @return List<IGenericContextInformation>
     */
    public static List<IGenericContextInformation> toContextInformationList(List<ContextInformationForDB> contextInformationForDBList) {
        return contextInformationMapper.toContextInformationList(contextInformationForDBList);
    }

    /**
     * convert ContextInformationForDB to IGenericContextInformation.<BR/>
     *
     * @param contextInformationForDB ContextInformationForDB
     * @return IGenericContextInformation
     */
    public static IGenericContextInformation toContextInformaion(ContextInformationForDB contextInformationForDB) {
        return contextInformationMapper.toProcessorModelFromDb(contextInformationForDB);
    }

    /**
     * convert OrchestrationServiceForDB to IGenericOrchestrationService.<BR/>
     *
     * @param orchestrationServiceForDB OrchestrationServiceForDB
     * @return IGenericOrchestrationService
     */
    public static IGenericOrchestrationService toOrchestrationService(OrchestrationServiceForDB orchestrationServiceForDB) {
        return orchestrationServiceMapper.toProcessorModelFromDb(orchestrationServiceForDB);
    }

    /**
     * convert IGenericOrchestrationService to OrchestrationServiceForMQ.<BR/>
     *
     * @param orchestrationService IGenericOrchestrationService
     * @return OrchestrationServiceForMQ
     */
    public static OrchestrationServiceForMQ toOrchestrationServiceForMQ(IGenericOrchestrationService orchestrationService) {
        return orchestrationServiceMapper.toMqModelFromPs(orchestrationService);
    }

    /**
     * convert List<OrchestrationServiceForMQ> to List<IGenericOrchestrationService>.<BR/>
     *
     * @param orchestrationServiceForMQList List<OrchestrationServiceForMQ>
     * @return List<IGenericOrchestrationService>
     */
    public static List<IGenericOrchestrationService> toOrchestrationServiceList(List<OrchestrationServiceForMQ> orchestrationServiceForMQList) {
        return orchestrationServiceMapper.toOrchestrationServiceListFromMq(orchestrationServiceForMQList);
    }

    /**
     * convert to OrchestrationService model from OrchestrationServiceForMQ.<BR/>
     *
     * @param orchestrationServiceForMQ OrchestrationServiceForMQ
     * @return IGenericOrchestrationService
     */
    public static IGenericOrchestrationService toOrchestrationService(OrchestrationServiceForMQ orchestrationServiceForMQ) {
        return orchestrationServiceMapper.toProcessorModelFromMq(orchestrationServiceForMQ);
    }

    /**
     * convert List<IGenericCompositeVirtualObject> to List<CompositeVirtualObjectForMQ>.<BR/>
     *
     * @param compositeVirtualObjectList List<IGenericCompositeVirtualObject>
     * @return List<CompositeVirtualObjectForMQ>
     */
    public static List<CompositeVirtualObjectForMQ> toCompositevirtualObjectForMQList(List<IGenericCompositeVirtualObject> compositeVirtualObjectList) {
        return compositeVirtualObjectMapper.toCompositevirtualObjectForMQList(compositeVirtualObjectList);
    }

    /**
     * convert List<CompositeVirtualObjectForMQ> to List<IGenericCompositeVirtualObject>.<BR/>
     *
     * @param compositeVirtualObjectForMQList List<CompositeVirtualObjectForMQ>
     * @return List<IGenericCompositeVirtualObject>
     */
    public static List<IGenericCompositeVirtualObject> toCompositevirtualObjectList(List<CompositeVirtualObjectForMQ> compositeVirtualObjectForMQList) {
        return compositeVirtualObjectMapper.toCompositeVirtualObjectList(compositeVirtualObjectForMQList);
    }

    /**
     * convert CompositeVirtualObjectForMQ to IGenericCompositeVirtualObject.<BR/>
     *
     * @param compositeVirtualObjectForMQ CompositeVirtualObjectForMQ
     * @return IGenericCompositeVirtualObject
     */
    public static IGenericCompositeVirtualObject toCompositevirtualObject(CompositeVirtualObjectForMQ compositeVirtualObjectForMQ) {
        return compositeVirtualObjectMapper.toProcessorModelFromMq(compositeVirtualObjectForMQ);
    }

    /**
     * convert CompositeVirtualObjectForMQ to IGenericCompositeVirtualObject.<BR/>
     *
     * @param compositeVirtualObjectForDb CompositeVirtualObjectForDB
     * @return IGenericCompositeVirtualObject
     */
    public static IGenericCompositeVirtualObject toCompositeVirtualObject(CompositeVirtualObjectForDB compositeVirtualObjectForDb) {
        return compositeVirtualObjectMapper.toProcessorModelFromDb(compositeVirtualObjectForDb);
    }

    /**
     * convert List<CompositeVirtualObjectForDB> to List<IGenericVirtualObject>.<BR/>
     *
     * @param compositeVirtualObjectForDBList List<CompositeVirtualObjectForDB>
     * @return List<IGenericCompositeVirtualObject>
     */
    public static List<IGenericCompositeVirtualObject> toCompositeVirtualObjectList(List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList) {
        return compositeVirtualObjectMapper.toCompositeVirtualObjectListFromDb(compositeVirtualObjectForDBList);
    }

    /**
     * convent IGenericCompositeVirtualObject to CompositeVirtualObjectForMQ.<BR/>
     *
     * @param compositeVirtualObject IGenericCompositeVirtualObject
     * @return CompositeVirtualObjectForMQ
     */
    public static CompositeVirtualObjectForMQ toCompositeVirtualObjectForMQ(IGenericCompositeVirtualObject compositeVirtualObject) {
        return compositeVirtualObjectMapper.toMqModelFromPs(compositeVirtualObject);
    }

    /**
     * convert List<IGenericVirtualObject> to List<VirtualObjectForMQ>.<BR/>
     *
     * @param virtualObjectList List<IGenericVirtualObject>
     * @return List<VirtualObjectForMQ>
     */
    public static List<VirtualObjectForMQ> toVirtualObjectForMQList(List<IGenericVirtualObject> virtualObjectList) {
        return virtualObjectMapper.toVirtualObjectForMQList(virtualObjectList);
    }

    /**
     * convent IGenericVirtualObject to VirtualObjectForMQ.<BR/>
     *
     * @param virtualObject IGenericVirtualObject
     * @return VirtualObjectForMQ
     */
    public static VirtualObjectForMQ toVirtualObjectForMQ(IGenericVirtualObject virtualObject) {
        return virtualObjectMapper.toMqModelFromPs(virtualObject);
    }


    /**
     * convert List<VirtualObjectForMQ> to List<IGenericVirtualObject>.<BR/>
     *
     * @param virtualObjectForDbList List<VirtualObjectForDB>
     * @return List<IGenericVirtualObject>
     */
    public static List<IGenericVirtualObject> toVirtualObjectListByVirtualObjectForDbList(List<VirtualObjectForDB> virtualObjectForDbList) {
        return virtualObjectMapper.toVirtualObjectListFromDb(virtualObjectForDbList);
    }

    /**
     * convert List<VirtualObjectForMQ> to List<IGenericVirtualObject>.<BR/>
     *
     * @param virtualObjectForMQList List<VirtualObjectForMQ>
     * @return List<IGenericVirtualObject>
     */
    public static List<IGenericVirtualObject> toVirtualObjectList(List<VirtualObjectForMQ> virtualObjectForMQList) {
        return virtualObjectMapper.toVirtualObjectList(virtualObjectForMQList);
    }

    /**
     * convert to List<VirtualObjectForDB> model from List<IGenericVirtualObject>.<BR/>
     *
     * @param virtualObjectForDBList List<VirtualObjectForDB>
     * @return List<IGenericVirtualObject>
     */
    public static List<IGenericVirtualObject> toVirtualObjectListFromDb(List<VirtualObjectForDB> virtualObjectForDBList) {
        return virtualObjectMapper.toVirtualObjectListFromDb(virtualObjectForDBList);
    }

    /**
     * convert to VirtualObject model from VirtualObjectForMQ.<BR/>
     *
     * @param virtualObjectForDB VirtualObjectForDB
     * @return IGenericVirtualObject
     */
    public static IGenericVirtualObject toVirtualObject(VirtualObjectForDB virtualObjectForDB) {
        return virtualObjectMapper.toProcessorModelFromDb(virtualObjectForDB);
    }

    /**
     * convert to VirtualObject model from VirtualObjectForMQ.<BR/>
     *
     * @param virtualObjectForMQ VirtualObjectForMQ
     * @return IGenericVirtualObject
     */
    public static IGenericVirtualObject toVirtualObject(VirtualObjectForMQ virtualObjectForMQ) {
        return virtualObjectMapper.toProcessorModelFromMq(virtualObjectForMQ);
    }

    /**
     * convert AspectForDB to IGenericAspect.<BR/>
     *
     * @param aspectForDB AspectForDB
     * @return IGenericAspect
     */
    public static IGenericAspect toAspect(AspectForDB aspectForDB) {
        return aspectMapper.toProcessorModelFromDb(aspectForDB);
    }

    /**
     * convert AspectForMQ to IGenericAspect.<BR/>
     *
     * @param aspectForMQ AspectForMQ
     * @return IGenericAspect
     */
    public static IGenericAspect toAspect(AspectForMQ aspectForMQ) {
        return aspectMapper.toProcessorModelFromMq(aspectForMQ);
    }

    /**
     * convert AspectForDB to IGenericAspect.<BR/>
     *
     * @param aspect IGenericAspect
     * @return AspectForMQ
     */
    public static AspectForMQ toAspectForMQ(IGenericAspect aspect) {
        return aspectMapper.toMqModelFromPs(aspect);
    }

    /**
     * convert FunctionForDB to IGenericFunction.<BR/>
     *
     * @param functionForDB FunctionForDB
     * @return IGenericFunction
     */
    public static IGenericFunction toFunction(FunctionForDB functionForDB) {
        return functionMapper.toProcessorModelFromDb(functionForDB);
    }

    /**
     * convert FunctionForMQ to IGenericFunction.<BR/>
     *
     * @param functionForMQ FunctionForMQ
     * @return IGenericFunction
     */
    public static IGenericFunction toFunction(FunctionForMQ functionForMQ) {
        return functionMapper.toProcessorModelFromMq(functionForMQ);
    }

    /**
     * convert FunctionForMQ to IGenericFunction.<BR/>
     *
     * @param function IGenericFunction
     * @return FunctionForMQ
     */
    public static FunctionForMQ toFunctionForMQ(IGenericFunction function) {
        return functionMapper.toMqModelFromPs(function);
    }

    /**
     * DeviceForDB to IGenericVirtualDevice.<BR/>
     *
     * @param deviceForDB DeviceForDB
     * @return IGenericVirtualDevice
     */
    public static IGenericVirtualDevice toVirtualDevice(DeviceForDB deviceForDB) {
        return virtualDeviceMapper.toProcessorModelFromDb(deviceForDB);
    }

    /**
     * DeviceControlForMQ to IGenericVirtualDevice.<BR/>
     *
     * @param deviceControlForMQ DeviceControlForMQ
     * @return IGenericVirtualDevice
     */
    public static IGenericVirtualDevice toVirtualDevice(DeviceControlForMQ deviceControlForMQ) {
        return virtualDeviceMapper.toProcessorModelFromMq(deviceControlForMQ);
    }

    /**
     * DeviceControlForMQ to IGenericVirtualDevice.<BR/>
     *
     * @param virtualDevice IGenericVirtualDevice
     * @return DeviceControlForMQ
     */
    public static DeviceControlForMQ toVirtualDevice(IGenericVirtualDevice virtualDevice) {
        return virtualDeviceMapper.toMqModelFromPs(virtualDevice);
    }

    /**
     * List<DeviceForDB> to List<IGenericVirtualDevice>.<BR/>
     *
     * @param deviceForDbList List<DeviceForDB>
     * @return List<IGenericVirtualDevice>
     */
    public static List<IGenericVirtualDevice> toVirtualDeviceList(List<DeviceForDB> deviceForDbList) {
        return virtualDeviceMapper.toVirtualDeviceList(deviceForDbList);
    }

    /**
     * convert LocationForDB to Location.<BR/>
     *
     * @param locationForDB Location
     * @return IGenericLocation
     */
    public static IGenericLocation toLocation(LocationForDB locationForDB) {
        DefaultLocation location = null;
        if (locationForDB != null) {
            location = new DefaultLocation(locationForDB.getId(), locationForDB.getName(), locationForDB.getUri(),
                    locationForDB.getDescription());
        }
        return location;
    }

    /**
     * convert LocationForMQ object to IGenericLocation.<BR/>
     *
     * @param locationForMQ LocationForMQ object
     * @return IGenericLocation
     */
    public static IGenericLocation toLocation(LocationForMQ locationForMQ) {
        DefaultLocation location = null;
        if (locationForMQ != null) {
            location = new DefaultLocation();
            location.setId(locationForMQ.getId());
            location.setName(locationForMQ.getName());
            location.setDescription(locationForMQ.getDescription());
            location.setUri(locationForMQ.getUri());
            //BeanUtils.copyProperties(location, locationForMQ);
        }
        return location;
    }

    /**
     * convert List<LocationForMQ> to List<IGenericLocation>.<BR/>
     *
     * @param locationForMQlist List<LocationForMQ>
     * @return List<IGenericLocation>
     */
    public static List<IGenericLocation> toLocationList(List<LocationForMQ> locationForMQlist) {
        List<IGenericLocation> locationList = null;
        if (locationForMQlist != null) {
            locationList = new ArrayList<>();
            for (LocationForMQ locationForMQ : locationForMQlist) {
                locationList.add(ModelMapper.toLocation(locationForMQ));
            }
        }
        return locationList;
    }

    /**
     * Values of Identity instance to Custom<BR/>
     *
     * @param fromInstance
     * @param toClass
     * @param <T2>
     * @return converted Class instance.
     */
    public static <T2> T2 toIdentityInstance(IGenericIdentity fromInstance, Class<T2> toClass) {
        Object identityObj = null;
        try {
            //from
            Class<?> fromClass = fromInstance.getClass();
            //to
            identityObj = toClass.newInstance();
            //id
            Method mGetId = fromClass.getDeclaredMethod("getId");
            String id = (String) mGetId.invoke(fromInstance);
            Method mSetId = toClass.getDeclaredMethod("setId");
            mSetId.invoke(identityObj, id);
            //name
            Method mGetName = fromClass.getDeclaredMethod("getName");
            String name = (String) mGetName.invoke(fromInstance);
            Method mSetName = toClass.getDeclaredMethod("setName");
            mSetName.invoke(identityObj, name);
            //description
            Method mGetDescription = fromClass.getDeclaredMethod("getDescription");
            String description = (String) mGetDescription.invoke(fromInstance);
            Method mSetDescription = toClass.getDeclaredMethod("setDescription");
            mSetDescription.invoke(identityObj, description);
            //uri
            Method mGetUri = fromClass.getDeclaredMethod("getUri");
            String uri = (String) mGetUri.invoke(fromInstance);
            Method mSetUri = toClass.getDeclaredMethod("setUri");
            mSetUri.invoke(identityObj, uri);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return (T2) identityObj;
    }
}
