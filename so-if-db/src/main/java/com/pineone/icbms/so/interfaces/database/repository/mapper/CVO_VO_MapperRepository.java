package com.pineone.icbms.so.interfaces.database.repository.mapper;

import com.pineone.icbms.so.interfaces.database.model.mapper.CVO_VO_MapperForDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by melvin on 2017. 5. 10..
 */

@Repository
@Table(name ="CVO_VO")
public interface CVO_VO_MapperRepository extends JpaRepository<CVO_VO_MapperForDB, String> {
    //
    List<CVO_VO_MapperForDB> findAllByCompositeVirtualObjectId(String compositeVirtualObjectId);
//    CVO_VO_MapperForDB findByCompositeVirtualObjectId(String compositeVirtualObjectId);
}
