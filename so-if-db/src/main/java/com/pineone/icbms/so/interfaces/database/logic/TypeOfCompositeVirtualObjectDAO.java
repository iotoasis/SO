package com.pineone.icbms.so.interfaces.database.logic;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.TypeOfCompositeVirtualObjectData;
import com.pineone.icbms.so.interfaces.database.logic.itf.ITypeOfCompositeVirtualObjectDAO;
import com.pineone.icbms.so.interfaces.database.model.TypeOfCompositeVirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.repository.TypeOfCompositeVirtualObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 30..
 */
@Service
//  TypeOfCompositeVirtualObjectRepository Data Access 기능 구현
public class TypeOfCompositeVirtualObjectDAO implements ITypeOfCompositeVirtualObjectDAO {

    @Autowired
    TypeOfCompositeVirtualObjectRepository TypeOfCompositeVirtualObjectRepository;

    // TypeOfCompositeVirtualObjectRepository 단일 조회
    @Override
    public TypeOfCompositeVirtualObjectForDB retrieveTypeOfCompositeVirtualObject(String id) {
        return TypeOfCompositeVirtualObjectRepository.findOne(id);
    }

    // TypeOfCompositeVirtualObjectRepository 전체 조회
    @Override
    public List<TypeOfCompositeVirtualObjectForDB> retrieveTypeOfCompositeVirtualObjectList() {
        return TypeOfCompositeVirtualObjectRepository.findAll();
    }

    // TypeOfCompositeVirtualObjectRepository 생성 기능
    @Override
    public String createTypeOfCompositeVirtualObject(TypeOfCompositeVirtualObjectData TypeOfCompositeVirtualObjectData) {
        return null;
    }

    // TypeOfCompositeVirtualObjectRepository 갱신 기능
    @Override
    public String updateTypeOfCompositeVirtualObject(String id, TypeOfCompositeVirtualObjectData TypeOfCompositeVirtualObjectData) {
        return null;
    }

    //  TypeOfCompositeVirtualObjectRepository 삭제 기능
    @Override
    public String deleteTypeOfCompositeVirtualObject(String id) {
        TypeOfCompositeVirtualObjectForDB TypeOfCompositeVirtualObjectForDB = TypeOfCompositeVirtualObjectRepository.findOne(id);
        TypeOfCompositeVirtualObjectRepository.delete(id);
        String message = "Delete  " + TypeOfCompositeVirtualObjectForDB;
        return message;
    }
}
