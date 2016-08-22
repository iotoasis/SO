package com.pineone.icbms.so.contextmodel;

import com.pineone.icbms.so.domain.DomainApplication;
import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.domain.pr.DomainPresentation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    public void domain() throws Exception {

        List<Domain> domainList = domainPresentation.retrieveDomainListController();
        for (Domain domain : domainList) {
            System.out.println(domain.getName());
            System.out.println(domain.getUri());
        }
    }
}
