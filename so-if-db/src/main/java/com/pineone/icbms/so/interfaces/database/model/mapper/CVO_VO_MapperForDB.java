package com.pineone.icbms.so.interfaces.database.model.mapper;

import javax.persistence.*;

/**
 * Created by melvin on 2017. 5. 10..
 */

//@SequenceGenerator(
//        name = "CVO_MAPPER_SEQ_GENERATOR",
//        sequenceName = "CVO_MAPPER_SEQ", //매핑할데이터베이스 시퀀스 이름
//        initialValue = 2, allocationSize = 1)
@Entity
@Table(name = "CVO_VO")
public class CVO_VO_MapperForDB {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CVO_MAPPER_SEQ_GENERATOR")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

    @Column(name = "composite_virtual_object_id")
    private String compositeVirtualObjectId;

    @Column(name = "virtual_object_id")
    private String virtualObjectId;

    public CVO_VO_MapperForDB() {
    }

    public CVO_VO_MapperForDB(String id, String compositeVirtualObjectId, String virtualObjectId) {
        this.id = id;
        this.compositeVirtualObjectId = compositeVirtualObjectId;
        this.virtualObjectId = virtualObjectId;
    }
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompositeVirtualObjectId() {
        return compositeVirtualObjectId;
    }

    public void setCompositeVirtualObjectId(String compositeVirtualObjectId) {
        this.compositeVirtualObjectId = compositeVirtualObjectId;
    }

    public String getVirtualObjectId() {
        return virtualObjectId;
    }

    public void setVirtualObjectId(String virtualObjectId) {
        this.virtualObjectId = virtualObjectId;
    }

    public CVO_VO_MapperForDB(String compositeVirtualObjectId, String virtualObjectId) {
        this.compositeVirtualObjectId = compositeVirtualObjectId;
        this.virtualObjectId = virtualObjectId;
    }

    @Override
    public String toString() {
        return "CVO_VO_MapperForDB{" +
                "id='" + id + '\'' +
                ", compositeVirtualObjectId='" + compositeVirtualObjectId + '\'' +
                ", virtualObjectId='" + virtualObjectId + '\'' +
                '}';
    }
}
