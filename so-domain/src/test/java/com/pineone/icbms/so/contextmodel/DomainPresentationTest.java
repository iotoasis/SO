package com.pineone.icbms.so.contextmodel;

import com.pineone.icbms.so.domain.DomainApplication;
import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.domain.pr.DomainPresentation;
import com.pineone.icbms.so.domain.ref.Domain_Note;
import com.pineone.icbms.so.domain.ref.ResponseMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = DomainApplication.class)
public class DomainPresentationTest {

    @Autowired
    DomainPresentation domainPresentation;

    @Test
    public void createDomain() throws Exception {

        Domain domain = new Domain();
        domain.setId("DO-CLASSROOM");
        domain.setName(Domain_Note.NAME_CLASSROOM);
        domain.setUri(Domain_Note.URI_CLASSROOM);

        ResponseMessage responseMessage = domainPresentation.registerDomainController(domain);
        System.out.println(responseMessage.getMessage());

        Domain domain1= new Domain();
        domain1.setId("DO-LABORATORY");
        domain1.setName(Domain_Note.NAME_LABORATORY);
        domain1.setUri(Domain_Note.URI_LABORATORY);

        ResponseMessage responseMessage1 =domainPresentation.registerDomainController(domain1);
        System.out.println(responseMessage1.getMessage());
        System.out.println();
    }

    @Test
    public void 도메인개별조회() throws Exception {

        Domain domain = domainPresentation.retrieveDomain("DO-CLASSROOM");
        System.out.println(domain.getName());
        System.out.println(domain.getUri());
    }

    @Test
    public void 도메인리스트조회() throws Exception {
        List<Domain> domainList = domainPresentation.retrieveDomainListController();
        for (Domain domain : domainList){
            System.out.println(domain.getName());
            System.out.println(domain.getUri());
        }
    }
}
