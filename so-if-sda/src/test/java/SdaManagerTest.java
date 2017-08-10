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
    public void retrieveFunctionListByLoc() throws Exception {
        //
        String locationId = "http://www.iotoasis.org/ontology/t1eng_605";
        sdaManager.retrieveFunctionListInLocation(locationId);
    }

    @Test
    public void retrieveAspectList() throws Exception {
        //
        String functionId = "http://www.iotoasis.org/ontology/SwitchFunction";
        sdaManager.retrieveAspectListByFunction(functionId);
    }

    @Test
    public void retrieveDeviceListByFuncAndLoc() throws Exception {
        //
        String locationId = "http://www.iotoasis.org/ontology/t1eng_605";
        String functionId = "http://www.iotoasis.org/ontology/MeasureFunction";
        sdaManager.retrieveDeviceListByFunctionAndLocation(locationId, functionId);
    }

    @Test
    public void retrieveDeviceListByFunc() throws Exception {
        //
        String functionId = "http://www.iotoasis.org/ontology/MeasureFunction";
        sdaManager.retrieveDeviceListByFunction(functionId);
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
