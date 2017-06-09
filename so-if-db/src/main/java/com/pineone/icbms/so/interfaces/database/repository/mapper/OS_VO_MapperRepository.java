package com.pineone.icbms.so.interfaces.database.repository.mapper;

import com.pineone.icbms.so.interfaces.database.model.mapper.OS_VO_MapperForDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by melvin on 2017. 5. 10..
 */

@Repository
@Table(name ="OS_VO")
public interface OS_VO_MapperRepository extends JpaRepository<OS_VO_MapperForDB, String> {
    //
    List<OS_VO_MapperForDB> findAllByOrchestrationServiceId(String orchestrationId);
}
