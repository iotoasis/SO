import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2017. 4. 18..
 */

public class Jackson_Test {

    @Test
    public void name() throws Exception {
        List<TestObject> testObjectList = new ArrayList<>();

        TestObject testObject1 = new TestObject("5", "5", "5");
        TestObject testObject2 = new TestObject("6", "6", "6");
        TestObject testObject3 = new TestObject("7", "7", "7");

        testObjectList.add(testObject1);
        testObjectList.add(testObject2);
        testObjectList.add(testObject3);

        ObjectMapper objectMapper = new ObjectMapper();

        // List --> String
        String testObjListStr = objectMapper.writeValueAsString(testObjectList);
        System.out.println("*******funcListStr******** " + testObjListStr);

        // String --> List
        List<TestObject> testObjects = objectMapper.readValue(testObjListStr, new TypeReference<ArrayList<TestObject>>() {
        });
        System.out.println("*******funcList******** " + testObjects);

        // Class --> String
        TestObject testObject = testObjects.get(0);
        String testObjStr = objectMapper.writeValueAsString(testObject);
        System.out.println("*******funcStr******** " + testObjStr);

        // String --> Class
        TestObject testClass = objectMapper.readValue(testObjStr, TestObject.class);
        System.out.println("*******funcClass******** " + testClass);
        System.out.println("*******funcClass.name******** " + testClass.getName());


    }

}

