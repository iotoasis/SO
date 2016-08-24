package com.pineone.icbms.so.servicemodel;

import com.pineone.icbms.so.contextmodel.pr.ContextModelPresentation;
import com.pineone.icbms.so.profile.ProfileApplication;
import com.pineone.icbms.so.profile.entity.Profile;
import com.pineone.icbms.so.profile.pr.ProfilePresentation;
import com.pineone.icbms.so.profile.ref.ResponseMessage;
import com.pineone.icbms.so.profile.store.ProfileStore;
import com.pineone.icbms.so.servicemodel.pr.ServiceModelPresentation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 23..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ProfileApplication.class)
public class ProfilePresentationTest {

    @Autowired
    ProfilePresentation profilePresentation;

    @Autowired
    ServiceModelPresentation serviceModelPresentation;

    @Autowired
    ContextModelPresentation contextModelPresentation;

    @Autowired
    ProfileStore profileStore;

    @Before
    public void 프로파일등록() throws Exception {

        Profile profile = new Profile();
        profile.setId("PR-FIRE-ENV");
        profile.setName("화재진압자동화");

        List<String> contextModelIdList = contextModelPresentation.retrieveContextModelIdList();
        for(String contextModelId : contextModelIdList){
            System.out.println(contextModelId);
        }

        List<String> serviceModelIdList = serviceModelPresentation.retrieveServiceModelList();
        for(String serviceModelId : serviceModelIdList){
            System.out.println(serviceModelId);
        }

        profile.setContextModelId("CM-FIRE-EMERGENCY");
        profile.setServiceModelId("SM_IDEAL_COOL_TEMP");
        profile.setPeriod(0);

        ResponseMessage responseMessage = profilePresentation.registerProfileController(profile);
        System.out.println(responseMessage.getMessage());
    }

    @Test
    public void 프로파일리스트조회() throws Exception {

        List<String> profileIdList = profilePresentation.retrieveProfileIdList();

        for(String profileId : profileIdList){
            System.out.println(profileId);
        }
    }

    @Test
    public void 프로파일개별조회() throws Exception {

        Profile profile = profilePresentation.retrieveProfileDetailController("PR-FIRE-ENV");
        System.out.println(profile.getId());
        System.out.println(profile.getName());
        System.out.println(profile.getServiceModelId());
        System.out.println(profile.getContextModelId());
        System.out.println(profile.getPeriod());
    }

    @Test
    public void findByContextModelIdTest() throws Exception {


        List<Profile> profileList = profileStore.findByContextModelId("CM-FIRE-EMERGENCY");
        System.out.println("************************* ProfileSearching *******************");
        for(Profile profile : profileList){
            System.out.println(profile.getId());
            System.out.println(profile.getName());
            System.out.println(profile.getServiceModelId());
            System.out.println(profile.getContextModelId());
            System.out.println(profile.getPeriod());
        }
    }
}
