package com.pineone.icbms.so;


import com.pineone.icbms.so.context.contextmodel.domain.Domain;
import com.pineone.icbms.so.context.contextmodel.domain.Domain_SDA;
import com.pineone.icbms.so.context.util.address.AddressStore;
import com.pineone.icbms.so.context.contextmodel.pr.UserController_Domain;
import com.pineone.icbms.so.context.util.conversion.DataConversion;
import org.junit.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 7. 20..
 */
public class DomainTest {

    @Test
    public void domainRetrieveTest() throws Exception {

        //NOTE: Prepare
        Domain domainUsingSDAEx1 = Domain_SDA.newDomain();
        Domain domainUsingSDAEx2 = Domain_SDA.newDomain();
        domainUsingSDAEx1.setName("ClassRoom");
        domainUsingSDAEx2.setName("ComputerRoom");
        List<Domain> domainUsingSDAList = new ArrayList<>();
        domainUsingSDAList.add(domainUsingSDAEx1);
        domainUsingSDAList.add(domainUsingSDAEx2);
        String sendData = DataConversion.objectToString(domainUsingSDAList);
        ClientAndServer mockServer = ClientAndServer.startClientAndServer(18080);
        mockServer
                .when(HttpRequest.request().withMethod("GET")
                        .withPath(AddressStore.REGISTER_GENERALCONTEXT)
                .withBody(sendData))
                .respond(HttpResponse.response().withStatusCode(200).withBody(sendData));

        String readData = new RestTemplate().getForObject("http://localhost:18080" + AddressStore.RETRIEVE_DOMAIN,
                String.class);
        System.out.println(readData);
    }

    @Test
    public void name() throws Exception {
        ClientAndServer mockServer = ClientAndServer.startClientAndServer(18000);
        String message = "{message : \"Hello World \"}";
        mockServer
                .when(HttpRequest.request().withMethod("GET").withPath("/test"))
                .respond(HttpResponse.response().withStatusCode(200).withBody(message));
        String response = new RestTemplate().getForObject("http://localhost:18000/test", String.class);
        System.out.println("Response is " + response);
    }

    @Test
    public void multipleContextModelTest() throws Exception {
        Domain do1_1 = Domain_SDA.newDomain();
        Domain do1_2 = Domain_SDA.newDomain();
        Domain do1_3 = Domain_SDA.newDomain();
        Domain do1_4 = Domain_SDA.newDomain();
        Domain do1_5 = Domain_SDA.newDomain();
        Domain do1_6 = Domain_SDA.newDomain();
        Domain do2_1 = Domain_SDA.newDomain();
        Domain do2_2 = Domain_SDA.newDomain();
        Domain do2_3 = Domain_SDA.newDomain();
        Domain do2_4 = Domain_SDA.newDomain();
        Domain do2_5 = Domain_SDA.newDomain();
        Domain do2_6 = Domain_SDA.newDomain();
        Domain do3_1 = Domain_SDA.newDomain();
        Domain do3_2 = Domain_SDA.newDomain();
        Domain do3_3 = Domain_SDA.newDomain();
        Domain do3_4 = Domain_SDA.newDomain();
        Domain do3_5 = Domain_SDA.newDomain();
        Domain do3_6 = Domain_SDA.newDomain();

        do1_1.setName("do1_1");
        do1_2.setName("do1_2");
        do1_3.setName("do1_3");
        do1_4.setName("do1_4");
        do1_5.setName("do1_5");
        do1_6.setName("do1_6");
        List<Domain> do1_List = new ArrayList<>();
        do1_List.add(do1_1);
        do1_List.add(do1_2);
        do1_List.add(do1_3);
        do1_List.add(do1_4);
        do1_List.add(do1_5);
        do1_List.add(do1_6);

        do2_1.setName("do1_1");
        do2_2.setName("do2_2");
        do2_3.setName("do2_3");
        do2_4.setName("do2_4");
        do2_5.setName("do2_5");
        do2_6.setName("do2_6");
        List<Domain> do2_List = new ArrayList<>();
        do2_List.add(do2_1);
        do2_List.add(do2_2);
        do2_List.add(do2_3);
        do2_List.add(do2_4);
        do2_List.add(do2_5);
        do2_List.add(do2_6);

        do3_1.setName("do1_1");
        do3_2.setName("do3_2");
        do3_3.setName("do3_3");
        do3_4.setName("do3_4");
        do3_5.setName("do3_5");
        do3_6.setName("do3_6");

        List<Domain> do3_List = new ArrayList<>();
        do3_List.add(do3_1);
        do3_List.add(do3_2);
        do3_List.add(do3_3);
        do3_List.add(do3_4);
        do3_List.add(do3_5);
        do3_List.add(do3_6);

        for(Domain domainUsingSDA : do1_List){
            for(Domain domainUsingSDA1 : do2_List){
                for(Domain domainUsingSDA2 : do3_List){
                    if(domainUsingSDA2.getName() == domainUsingSDA1.getName()){
                        if(domainUsingSDA2.getName() == domainUsingSDA.getName()){
                            System.out.println(domainUsingSDA.getName());
                        }
                    }
                }
            }
        }
    }

    @Test
    public void domainTest() throws Exception {
        UserController_Domain userController_domain = new UserController_Domain();
        List<Domain> domainList = userController_domain.retrieveDomain();
        for(Domain domain : domainList){
            System.out.println(domain.getName());
            System.out.println(domain.getUri());
        }
        System.out.println();
    }
}
