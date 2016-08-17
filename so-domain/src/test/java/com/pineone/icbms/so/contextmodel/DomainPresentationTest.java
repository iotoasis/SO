package com.pineone.icbms.so.contextmodel;

import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.domain.pr.DomainPresentation;
import org.junit.Test;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 */
public class DomainPresentationTest {

    DomainPresentation domainPresentation = new DomainPresentation();

    @Test
    public void domain조회() throws Exception {

        List<Domain> domainList = domainPresentation.retrieveDomainListController();
        for (Domain domain : domainList) {
            System.out.println(domain.getName());
            System.out.println(domain.getUri());
        }
    }
}
