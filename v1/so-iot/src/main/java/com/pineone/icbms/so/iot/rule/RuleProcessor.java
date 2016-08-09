package com.pineone.icbms.so.iot.rule;

import com.pineone.icbms.so.interfaces.sda.client.NotExistDataException;
import com.pineone.icbms.so.interfaces.sda.client.NotExistDomainTypeException;
import com.pineone.icbms.so.iot.provider.ProfileProvider;
import com.pineone.icbms.so.iot.resources.occurrence.DefaultOccurrence;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;
import com.pineone.icbms.so.iot.resources.service.ServiceSketch;
import com.pineone.icbms.so.iot.rule.validation.RuleProcessorValidation;
import com.pineone.icbms.so.resources.domain.AGenericDomain;
import com.pineone.icbms.so.resources.domain.DefaultDomain;
import com.pineone.icbms.so.resources.model.repo.profile.DefaultProfileModel;
import com.pineone.icbms.so.resources.model.repo.service.DefaultServiceModel;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * role of RuleProcessor<BR/>
 * 1. Receive Occurrence, and connect DataBase to get ProfileList that Use occurrence.contextModelId<BR/>
 * 2. Get ServiceModel List From DataBase that Use Occurrence.contextModelId<BR/>
 * 3. RuleProcessor Validator<BR/>
 * 4. Set ServiceModel And Return <BR/>
 * Created by Melvin on 2015. 12. 23..
 */
public class RuleProcessor {
    /**
     * these Words judge type of Profile
     */
    public static final String locationType = "LOCATION";
    public static final String studentType = "STUDENT";

    private final Logger log = LoggerFactory.getLogger(RuleProcessor.class);


    /**
     * @param occurrence
     * @return
     */
    public ServiceSketch executeRule(DefaultOccurrence occurrence) {

        RuleProcessorValidation ruleProcessorValidation = new RuleProcessorValidation();
        ProfileProvider profileProvider = new ProfileProvider();
        List<DefaultProfileModel> profileModelArrayList;

        ArrayList<String> serviceModelIdList = new ArrayList<>();

        String contextId = occurrence.getContextId();
        ServiceSketch serviceSketch = new ServiceSketch();

//        IRecentOccurrenceChecker occurrenceChecker = null;

//        Boolean checker;


//        checker = occurrenceChecker.recentOccurrenceCheck(occurrence);

//        if (checker == true) {


        /**
         * Use Provider , Connect DataBase and get ProfileList<BR/>
         */
        try {

            /**
             connect DataBase to get ProfileList that Use occurrence.contextModelId<BR/>
             */
            profileModelArrayList = profileProvider.getDataListByContextID(contextId, DefaultProfileModel.class);

            log.info(" >> find Profile Using ContextModelId From Occ - CMid : " + occurrence.getContextId());

            ruleProcessorValidation.inspectProfiles(profileModelArrayList);


            /**
             * Extract Each Profile, Make ServiceSketch Include DomainList, DomainType(Location,Person),
             * ServiceModelIdList And Return<BR/>
             */


            List<? extends AGenericDomain> domainList = null;

            for (DefaultProfileModel profileModel : profileModelArrayList) {

                if (profileModel.getDomain().contains(locationType)) {
                    domainList = castLocation(occurrence.getDomainList());


                } else if (profileModel.getDomain().contains(studentType)) {
                    domainList = castStudent(occurrence.getDomainList());


                } else {

                    throw new NotExistDomainTypeException();
                }

                serviceSketch.setDomainList(domainList);
                serviceSketch.setDomainType(profileModel.getDomain());


                ruleProcessorValidation
                        .inspectServiceId(profileModel.getServiceModelList());

                for (DefaultServiceModel serviceModelUnit : profileModel.getServiceModelList()) {

                    serviceModelIdList.add(serviceModelUnit.getId());
                    log.info(" >> search Associated Service with ContextModel - serviceId : " + serviceModelUnit.getId());

                }
            }
            serviceSketch.setServiceModelIdList(serviceModelIdList);
        }


        /** If Validation catch Exception <BR/> */ catch (Exception e) {
            log.info(e.getMessage());
        }

//        for(DefaultServiceModel serviceModelUnit : serviceModelList){
//            serviceModelIdList.add(serviceModelUnit.getId());
//        }

        return serviceSketch;

    }

//        else
//            throw new NotUseSameDataForPeriod();
//    }





    /**
     * Judge Location Type Domain.<BR/>
     *
     * @param domainList
     * @return
     */
    public List<DefaultLocation> castLocation(List<DefaultDomain> domainList) {

        log.info(" >> Occurrence Type : Location");

        DefaultLocation location = new DefaultLocation();
        ArrayList<DefaultLocation> locationList = new ArrayList<>();

//        ArrayList<DefaultDomain> domainListTemp = new ArrayList<>();

//        domainListTemp.add(domainList.get(0));


//        for(DefaultDomain domain: domainList)
//        {
//            if(checkDomainLoc(domain, domainListTemp)){
//                domainListTemp.add(domain);
//            }
//        }



//
//        /**
//         * Check Duplication of occurrence.domains<BR/>
//         */
//        for (int j = 0; j < domainList.size(); j++) {
//
//            for (int k = j + 1; k < domainList.size(); k++) {
//
//                if (domainList.get(j).getLoc().equals(domainList.get(k).getLoc()) ||
//                        domainList.get(j).getId().equals(domainList.get(k).getId())) {
//
//                    log.info(" >> Delete Domain, Duplicate Domain");
//
//                    domainList.remove(k);
//
//                }
//            }
//        }


        for (DefaultDomain domain : domainList) {

            if (domain.getId() != null) location.setUri(domain.getId());
            else if (domain.getLoc() != null) location.setUri(domain.getLoc());
            else throw new NotExistDataException("Not Exist ID or Location Information.");

            locationList.add(location);
        }
            return locationList;

        }


    /**
     * Judge Person Type Domain<BR/>
     *
     * @param domainList
     * @return
     */
    public List<DefaultStudent> castStudent(List<DefaultDomain> domainList) {

        log.info(" >> Occurrence Type : Person");

        DefaultStudent student = new DefaultStudent();
        ArrayList<DefaultStudent> studentList = new ArrayList<>();

//        ArrayList<DefaultDomain> domainListTemp = new ArrayList<>();
//
////        domainListTemp.add(domainList.get(0));
//
//        for(DefaultDomain domain: domainList)
//        {
//            if(!checkDomainPerson(domain, domainListTemp)){
//
//                domainListTemp.add(domain);
//            }
//        }

            for (DefaultDomain domain : domainList) {

                if (domain.getId() != null) student.setUri(domain.getId());
                else if (domain.getPerson_id() != null) student.setUri(domain.getPerson_id());
                else throw new NotExistDataException("Not Exist ID ot Person_id. ");

                studentList.add(student);
            }


            return studentList;
        }
//
//    boolean checkDomainLoc(DefaultDomain domain, List<DefaultDomain> domainListTemp){
//
//        if(domainListTemp == null){
//            return true;
//        }
//
//        else {
//            for(DefaultDomain domain1 : domainListTemp) {
//                if (domain.getLoc().equals(domain1.getLoc()) || domain.getId().equals(domain1.getId())) {
//                    log.info(" >> Duplication , Delete Location Domain *********************************");
//                  return false;
//                }
//            }
//        }
//
//
//        return true;
//    }
//
//
//    boolean checkDomainPerson(DefaultDomain domain, List<DefaultDomain> domainListTemp){
//
//        boolean check = false;
//
//
//        for(DefaultDomain domain1 : domainListTemp) {
//            if (domain.getPerson_id().equals(domain1.getPerson_id()) || domain.getId().equals(domain1.getId())) {
//                log.info(" >> Duplication , Delete Person Domain *******************************");
//                check = true;
//                return check;
//            }
//
//        }
//
//        return check;
//    }
}





