import com.pineone.icbms.so.interfaces.sda.handle.SdaManager;
import com.pineone.icbms.so.interfaces.sda.ref.SdaAddressStore;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by melvin on 2017. 4. 24..
 */

public class SdaManagerTest {

    //@Autowired
    SdaManager sdaManager = new SdaManager();

//    public String cmId = SdaAddressStore.CM_FUNCTION_LIST;
    public String cmId = SdaAddressStore.CM_ASPECT_LIST;

//    @Ignore
    @Test
    public void retrieveFunctionList() throws Exception {
        List<String> resultList = sdaManager.retrieveListByContextModelId(cmId);
        System.out.println("size : " + resultList.size());
        System.out.println(resultList);
    }

    @Ignore
    //@Test
    public void retrieveContextModelEvent() throws Exception {

//        List<String> locationList = sdaManager.retrieveEventLocationList("cm-announcement-on");
        List<String> locationList1 = sdaManager.retrieveEventLocationList("cm-announcement-off");
//        System.out.println(locationList);
        System.out.println(locationList1);
    }

    @Ignore//@Test
    public void retrieveFunctionListByLoc() throws Exception {
        //
        String locationId = "http://www.iotoasis.org/ontology/t1eng_605";
        sdaManager.retrieveFunctionListInLocation(locationId);
    }

    @Ignore//@Test
    public void retrieveAspectList() throws Exception {
        //
        String functionId = "http://www.iotoasis.org/ontology/SwitchFunction";
        sdaManager.retrieveAspectListByFunction(functionId);
    }

    @Ignore//@Test
    public void retrieveDeviceListByFuncAndLoc() throws Exception {
        //
        String locationId = "http://www.iotoasis.org/ontology/t1eng_605";
        String functionId = "http://www.iotoasis.org/ontology/MeasureFunction";
        sdaManager.retrieveDeviceListByFunctionAndLocation(locationId, functionId);
    }

    @Ignore//@Test
    public void retrieveDeviceListByFunc() throws Exception {
        //
        String functionId = "http://www.iotoasis.org/ontology/MeasureFunction";
        sdaManager.retrieveDeviceListByFunction(functionId);
    }

    @Ignore//@Test
    public void retrieveDeviceListByLoc() throws Exception {
        //
        String locationId = "http://www.iotoasis.org/ontology/t1eng_605";
        sdaManager.retrieveDeviceListByLocation(locationId);
    }

    @Ignore//@Test
    public void retrieveContextListByCM() throws Exception {
        //
        String contextModelId = "cm-announcement-on";
        sdaManager.retrieveContextInformationList(contextModelId);
    }

    @Ignore//@Test
    public void retrieveValue() throws Exception {
        //
        String deviceId = "http://www.iotoasis.org/herit-in/herit-cse/ONDB_BeamProjector01_001";
        sdaManager.retrieveSensorValue(deviceId);
    }
}
