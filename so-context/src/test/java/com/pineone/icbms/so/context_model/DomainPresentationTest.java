package com.pineone.icbms.so.context_model;

import com.pineone.icbms.so.context_model.entity.Domain;
import com.pineone.icbms.so.context_model.pr.DomainPresentation;
import org.junit.Test;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 */
public class DomainPresentationTest {

    @Test
    public void 도메인조회테스트() throws Exception {
        List<Domain> domainList = new DomainPresentation().retrieveDomain();
        for(Domain domain : domainList){
            System.out.println(domain.getName());
            System.out.println(domain.getUri());
        }
    }
}
