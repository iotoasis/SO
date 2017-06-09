package com.pineone.icbms.so.interfaces.database.repository;

import com.pineone.icbms.so.interfaces.database.model.TypeOfCompositeVirtualObjectForDB;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by melvin on 2017. 3. 30..
 */
public interface TypeOfCompositeVirtualObjectRepository extends JpaRepository<TypeOfCompositeVirtualObjectForDB, String> {
}
