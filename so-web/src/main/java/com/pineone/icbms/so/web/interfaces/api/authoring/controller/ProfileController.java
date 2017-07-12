package com.pineone.icbms.so.web.interfaces.api.authoring.controller;

import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * context for ProfileForDB.<BR/>
 *
 * Created by uni4love on 2017. 1. 13..
 */
@RestController
@RequestMapping("/profile/*")
public class ProfileController {
    /**
     * logger
     */
	protected Logger log = LoggerFactory.getLogger(getClass());
	
    /**
     * repository(DAO)
     */
//    @Autowired
//    private ProfileDao dao;

//    /**
//     * constructor.<BR/>
//     *
//     * @param profileRepository
//     */
//    @Autowired
//    public ProfileController(ProfileRepository profileRepository) {
//        this.repository = profileRepository;
//    }

    /**
     * response for request "/profile, HTTP-method:POST".<BR/>
     * @param profile ProfileForDB
     * @return created profile id
     */
//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.OK)
//    public String createProfile(@RequestBody ProfileForDB profile) {
//    	repository.save(profile);
//        return String.valueOf(profile.getId());
//    }

    /**
     * response for request "/profile/{id}" .<BR/>
     * @param id profile id
     * @return profile
     */
//    @RequestMapping(value = "/{id}")
//    public ProfileForDB getProfile(@RequestParam("id") String id) {
//        ProfileForDB profile = repository.findOne(id);
//        return profile;
//    }

    /**
     * response for request "/profile".<BR/>
     * @return profile list
     */
//    @RequestMapping()
//    public List<ProfileForDB> getProfileList() {
//        List<ProfileForDB> profileList = repository.findAll();
//        return profileList;
//    }

    /**
     * response for request "/profile, HTTP-method:PATCH(update)".<BR/>
     * @param profile ProfileForDB
     * @return updated profile id
     */
    @RequestMapping(method=RequestMethod.PATCH)
    public String updateProfile(ProfileForDB profile)
    {
        //implements...
        return null;
    }

    /**
     * response for request "/profile/{id}, HTTP-method:DELETE".<BR/>
     * @param id profile id
     * @return deleted profile id
     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public String deleteProfile(@RequestParam("id") String id) {
//        repository.delete(id);
//        return id;
//    }

    /**
     * response for request "/profile/{id}, HTTP-method:POST, 'register'".<BR/>
     * @param id ProfileForDB id
     * @return registed profile id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String registerProfile(String id) {
        //implements...
        return null;
    }
}
