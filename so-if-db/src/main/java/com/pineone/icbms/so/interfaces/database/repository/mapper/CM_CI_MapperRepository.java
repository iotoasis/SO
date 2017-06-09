package com.pineone.icbms.so.interfaces.database.repository.mapper;

import com.pineone.icbms.so.interfaces.database.model.mapper.CM_CI_MapperForDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by melvin on 2017. 5. 11..
 */

@Repository
@Table(name ="CM_CI")
public interface CM_CI_MapperRepository extends JpaRepository<CM_CI_MapperForDB, String> {
    //
    List<CM_CI_MapperForDB> findAllByContextModelId(String contextModelId);
}
