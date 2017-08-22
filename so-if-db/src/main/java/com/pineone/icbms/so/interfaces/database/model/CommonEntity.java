package com.pineone.icbms.so.interfaces.database.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * handler entity model.<BR/>
 * Created by uni4love on 2017. 1. 13..
 */
//@Data
//@ToString
public class CommonEntity { //extends AbstractPersistable<String> {
    /*
     * id
     */
    @Getter @Setter
    private String id;

    /*
     * 이름
     */
    @Getter @Setter
    String name;

    /*
     * 정의
     */
    @Getter @Setter
    String description;

    /*
     * 생성일
     */
    @Getter @Setter
    String createdDate;

    /*
     * 수정일
     */
    @Getter @Setter
    String modifiedDate;

    /*
     * 권한기능 - 사용자 ID
     */
    @Getter @Setter
    String userId;

    /*
     * 차수 - 1, 2, 3 차년
     */
    @Getter @Setter
    String orderCd;

}
