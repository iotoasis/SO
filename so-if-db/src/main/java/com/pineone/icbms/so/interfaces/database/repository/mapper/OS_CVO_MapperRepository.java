package com.pineone.icbms.so.interfaces.database.repository.mapper;

import com.pineone.icbms.so.interfaces.database.model.mapper.OS_CVO_MapperForDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by melvin on 2017. 5. 15..
 */

@Repository
@Table(name ="OS_CVO")
public interface OS_CVO_MapperRepository extends JpaRepository<OS_CVO_MapperForDB, String> {
    //
    List<OS_CVO_MapperForDB> findAllByOrchestrationServiceId(String orchestrationServiceId);
}
