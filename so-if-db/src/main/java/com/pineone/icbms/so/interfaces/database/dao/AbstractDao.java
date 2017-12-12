package com.pineone.icbms.so.interfaces.database.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//import java.util.List;

/**
 * Created by jonghee on 2017-06-22.
 */
public class AbstractDao {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public SqlSession sqlSession;

//    private T resultType;
//
//    public T selectOne(String sqlId) {
//        return this.sqlSession.selectOne(sqlId, resultType);
//    }
//
//    public List<T> selectList(String sqlId) {
//        return this.sqlSession.selectList(sqlId, resultType);
//    }
//
//    public List<T> selectList(String sqlId, String id) {
//        return this.sqlSession.selectList(sqlId, id);
//    }
//
//    public int insert(String sqlId) {
//        return this.sqlSession.insert(sqlId, resultType);
//    }
//
//    public int delete(String sqlId) {
//        return this.sqlSession.delete(sqlId, resultType);
//    }
//
//    public int update(String sqlId) {
//        return this.sqlSession.update(sqlId, resultType);
//    }
}