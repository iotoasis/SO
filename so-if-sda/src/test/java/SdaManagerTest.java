import com.pineone.icbms.so.interfaces.sda.handle.SdaManager;
import org.junit.Test;

import java.util.List;

/**
 * Created by melvin on 2017. 4. 24..
 */

public class SdaManagerTest {

    SdaManager sdaManager = new SdaManager();

    @Test
    public void retrieveContextModelEvent() throws Exception {

//        List<String> locationList = sdaManager.retrieveEventLocationList("cm-announcement-on");
        List<String> locationList1 = sdaManager.retrieveEventLocationList("cm-announcement-off");
//        System.out.println(locationList);
        System.out.println(locationList1);
    }

    @Test
    public void retrieveFunctionalityListByLoc() throws Exception {
        //
        String locationId = "http://www.iotoasis.org/ontology/t1eng_605";
        sdaManager.retrieveFunctionalityListInLocation(locationId);
    }

    @Test
    public void retrieveAspectList() throws Exception {
        //
        String functionalityId = "http://www.iotoasis.org/ontology/SwitchFunctionality";
        sdaManager.retrieveAspectListByFunctionality(functionalityId);
    }

    @Test
    public void retrieveDeviceListByFuncAndLoc() throws Exception {
        //
        String locationId = "http://www.iotoasis.org/ontology/t1eng_605";
        String functionalityId = "http://www.iotoasis.org/ontology/MeasureFunctionality";
        sdaManager.retrieveDeviceListByFunctionalityAndLocation(locationId, functionalityId);
    }

    @Test
    public void retrieveDeviceListByFunc() throws Exception {
        //
        String functionalityId = "http://www.iotoasis.org/ontology/MeasureFunctionality";
        sdaManager.retrieveDeviceListByFunctionality(functionalityId);
    }

    @Test
    public void retrieveDeviceListByLoc() throws Exception {
        //
        String locationId = "http://www.iotoasis.org/ontology/t1eng_605";
        sdaManager.retrieveDeviceListByLocation(locationId);
    }

    @Test
    public void retrieveContextListByCM() throws Exception {
        //
        String contextModelId = "cm-announcement-on";
        sdaManager.retrieveContextInformationList(contextModelId);
    }

    @Test
    public void retrieveValue() throws Exception {
        //
        String deviceId = "http://www.iotoasis.org/herit-in/herit-cse/ONDB_BeamProjector01_001";
        sdaManager.retrieveSensorValue(deviceId);
    }
}
