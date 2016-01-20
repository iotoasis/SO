package com.pineone.icbms.so.interfaces.sda.collector.provider;

import com.pineone.icbms.so.interfaces.sda.client.CollectorController;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseMapper;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseModel;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;

import java.util.ArrayList;

/**
 * 필요한 정보를 입력받아서 Uri를 만들어 연관된 장소를 조회
 * Created by Melvin on 2016. 1. 11..
 */
public class LocationInfoProvider {

    /**
     * 지정 요일의 강의실을 검색하기 위한 컨텍스트가 포함된 주소
     */
    public static final String cm100Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-100/?p=";

    /**
     * 인물의 현재 위치를 검색하기 위한 컨텍스트가 포함된 주소
     */
    public static final String cm200Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-200/?p=";

    /**
     * 장소의 현재 온도의 적절성에 대해 조회하기 위한 컨텍스트가 포함된 주소
     */
    public static final String cm230Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-230/?p=";

    /**
     * 장소의 현재 습도의 적절성에 대해 조회하기 위한 컨텍스트가 포함된 주소
     */
    public static final String cm240Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-240/?p=";

    /**
     * 장소의 현재 조도의 적절성에 대해 조회하기 위한 컨텍스트가 포함된 주소
     */
    public static final String cm250Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-250/?p=";



    /**
     * 요일과 시간을 입력받아서 강의가 예정되어있는지 조회
     *
     * @param date
     * @param time
     * @return
     */
    public ArrayList<DefaultLocation> getLocationInfoFromSDA(String date, String time) {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel;
        ArrayList<DefaultLocation> locationArrayList = new ArrayList<>();
        DefaultLocation location;

        /**
         * 요일과 시간 내용을 입력받아서 장소 정보를 가지고 올 수 있는 URL을 생성.
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-100/?p=monday,0850
         *
         */
        String getLocationURL = cm100Host + date + "," + time;

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getLocationURL);


        /**
         * Json형태를 띄고 있는 String형 데이터를 Object로 변환
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** local 정보만 String 으로 추출하여 반환
         */

        for (int i = 0; i < responseModel.getContent().size(); i++) {
            location = new DefaultLocation();
            location.setUri(responseModel.getContent().get(i).getLoc());
            locationArrayList.add(location);
        }

        return locationArrayList;
    }

    /**
     * 사람이 현재 있는 장소를 조회
     */
    public ArrayList<DefaultLocation> getCurrentLocationFromSDA(DefaultStudent student) {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel;
        ArrayList<DefaultLocation> locationArrayList = new ArrayList<>();
        DefaultLocation location;

        /**
         * 요일과 시간 내용을 입력받아서 장소 정보를 가지고 올 수 있는 URL을 생성.
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-200/?p=http://www.pineone.com/campus/u00002
         *
         */
        String getLocationURL = cm200Host + student.getUri();

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getLocationURL);


        /**
         * Json형태를 띄고 있는 String형 데이터를 Object로 변환
         */
        responseModel = responseMapper.getList(afterConnectData);

        for (int i = 0; i < responseModel.getContent().size(); i++) {
            location = new DefaultLocation();
            location.setUri(responseModel.getContent().get(i).getLoc());
            locationArrayList.add(location);
        }

        return locationArrayList;
    }

    public ArrayList<DefaultLocation> getHotLocationInfo(DefaultLocation location) {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel;
        ArrayList<DefaultLocation> locationArrayList = new ArrayList<>();

        /**
         * 조회가 필요한 장소의 정보를 추가하여 URL을 생성
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-230/? + location
         *
         */
        String getLocationURL = cm230Host + location.getUri();

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getLocationURL);


        /**
         * 적정 온도 이상의 강의실이 판별이 된다면 장소를 리턴
         */
        if (afterConnectData.contains("Hot")) {

            /**
             * Json형태를 띄고 있는 String형 데이터를 Object로 변환
             */
            responseModel = responseMapper.getList(afterConnectData);

            for (int i = 0; i < responseModel.getContent().size(); i++) {
                location = new DefaultLocation();
                location.setUri(responseModel.getContent().get(i).getLoc());
                locationArrayList.add(location);
            }

            return locationArrayList;
        } else {

            /*TODO : Exception 정책 */
            return null;
        }
    }

    public ArrayList<DefaultLocation> getColdLocationInfo(DefaultLocation location) {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel;
        ArrayList<DefaultLocation> locationArrayList = new ArrayList<>();

        /**
         * 조회가 필요한 장소의 정보를 추가하여 URL을 생성
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-230/? + location
         *
         */
        String getLocationURL = cm230Host + location.getUri();

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getLocationURL);


        /**
         * 적정 온도 이하의 강의실이 판별이 된다면 장소를 리턴
         */
        if (afterConnectData.contains("Cold")) {

            /**
             * Json형태를 띄고 있는 String형 데이터를 Object로 변환
             */
            responseModel = responseMapper.getList(afterConnectData);

            for (int i = 0; i < responseModel.getContent().size(); i++) {
                location = new DefaultLocation();
                location.setUri(responseModel.getContent().get(i).getLoc());
                locationArrayList.add(location);
            }

            return locationArrayList;
        } else {

            /*TODO : Exception 정책 */
            return null;
        }
    }

    public ArrayList<DefaultLocation> getWetLocationInfo(DefaultLocation location) {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel;
        ArrayList<DefaultLocation> locationArrayList = new ArrayList<>();

        /**
         * 조회가 필요한 장소의 정보를 추가하여 URL을 생성
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-240/? + location
         *
         */
        String getLocationURL = cm240Host + location.getUri();

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getLocationURL);


        /**
         * 적정 습도 이상의 강의실이 판별이 된다면 장소를 리턴
         */
        if (afterConnectData.contains("Wet")) {

            /**
             * Json형태를 띄고 있는 String형 데이터를 Object로 변환
             */
            responseModel = responseMapper.getList(afterConnectData);

            for (int i = 0; i < responseModel.getContent().size(); i++) {
                location = new DefaultLocation();
                location.setUri(responseModel.getContent().get(i).getLoc());
                locationArrayList.add(location);
            }

            return locationArrayList;
        } else {

            /*TODO : Exception 정책 */
            return null;
        }
    }

    public ArrayList<DefaultLocation> getDryLocationInfo(DefaultLocation location) {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel;
        ArrayList<DefaultLocation> locationArrayList = new ArrayList<>();

        /**
         * 조회가 필요한 장소의 정보를 추가하여 URL을 생성
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-240/? + location
         *
         */
        String getLocationURL = cm240Host + location.getUri();

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getLocationURL);


        /**
         * 적정 습도 이하의 강의실이 판별이 된다면 장소를 리턴
         */
        if (afterConnectData.contains("Dry")) {

            /**
             * Json형태를 띄고 있는 String형 데이터를 Object로 변환
             */
            responseModel = responseMapper.getList(afterConnectData);

            for (int i = 0; i < responseModel.getContent().size(); i++) {
                location = new DefaultLocation();
                location.setUri(responseModel.getContent().get(i).getLoc());
                locationArrayList.add(location);
            }

            return locationArrayList;
        } else {

            /*TODO : Exception 정책 */
            return null;
        }
    }

    public ArrayList<DefaultLocation> getBrightLocationInfo(DefaultLocation location) {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel;
        ArrayList<DefaultLocation> locationArrayList = new ArrayList<>();

        /**
         * 조회가 필요한 장소의 정보를 추가하여 URL을 생성
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-250/? + location
         *
         */
        String getLocationURL = cm250Host + location.getUri();

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getLocationURL);


        /**
         * 적정 조도 이의 강의실이 판별이 된다면 장소를 리턴
         */
        if (afterConnectData.contains("Bright")) {

            /**
             * Json형태를 띄고 있는 String형 데이터를 Object로 변환
             */
            responseModel = responseMapper.getList(afterConnectData);

            for (int i = 0; i < responseModel.getContent().size(); i++) {
                location = new DefaultLocation();
                location.setUri(responseModel.getContent().get(i).getLoc());
                locationArrayList.add(location);
            }

            return locationArrayList;
        } else {

            /*TODO : Exception 정책 */
            return null;
        }
    }

    public ArrayList<DefaultLocation> getDarkLocationInfo(DefaultLocation location) {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel;
        ArrayList<DefaultLocation> locationArrayList = new ArrayList<>();

        /**
         * 조회가 필요한 장소의 정보를 추가하여 URL을 생성
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-250/? + location
         *
         */
        String getLocationURL = cm250Host + location.getUri();

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getLocationURL);


        /**
         * 적정 조도 이하의 강의실이 판별이 된다면 장소를 리턴
         */
        if (afterConnectData.contains("Dark")) {

            /**
             * Json형태를 띄고 있는 String형 데이터를 Object로 변환
             */
            responseModel = responseMapper.getList(afterConnectData);

            for (int i = 0; i < responseModel.getContent().size(); i++) {
                location = new DefaultLocation();
                location.setUri(responseModel.getContent().get(i).getLoc());
                locationArrayList.add(location);
            }

            return locationArrayList;
        } else {

            /*TODO : Exception 정책 */
            return null;
        }
    }




}
