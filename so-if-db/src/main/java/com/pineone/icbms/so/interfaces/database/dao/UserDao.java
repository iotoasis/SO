package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.AspectForDB;
import com.pineone.icbms.so.interfaces.database.model.UserForDB;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 28..
 */
@Component
public class UserDao extends AbstractDao {//<UserForDB> {
    
    // retrieve one
    public UserForDB retrieve(String id) {
        return super.sqlSession.selectOne("retrieveUserById", id);
    }

    // retrieve list
    public List<UserForDB> retrieve(UserForDB model) {
        return super.sqlSession.selectList("retrieveUserByModel", model);
    }

    // retrieve all
    public List<UserForDB> retrieve() {
        return super.sqlSession.selectList("retrieveUserByModel");
    }

    // 저장 기능 구현
    public UserForDB create(UserForDB model) {
        super.sqlSession.insert("createUser", model);
        return super.sqlSession.selectOne("retrieveUserById", model.getId());
    }

    // 갱신 기능 구현
    public int update(UserForDB model) {
        //
        return super.sqlSession.update("updateUser", model);
    }

    // 삭제 기능 구현
    public int delete(String id) {
        return super.sqlSession.delete("deleteUser", id);
    }

}