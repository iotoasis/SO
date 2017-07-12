package com.pineone.icbms.so.interfaces.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@SpringBootApplication
//@ComponentScan(basePackages = "com.pineone.icbms.so")
//@EntityScan(basePackages = "com.pineone.icbms.so")
//@EnableAutoConfiguration
//@EnableJpaRepositories(basePackages = "com.pineone.icbms.so")
//@MapperScan(value = {"com.pineone.icbms.so.interfaces.database.service"})
public class SoIfDbApplication { //implements CommandLineRunner{
    public SoIfDbApplication() {
    }

    /**
     * run application.<BR/>
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SoIfDbApplication.class, args);
    }

    /**
     * SqlSessionFactory 설정
     */
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception{
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//
//        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath*:**/mappers/*.xml");
//
//        sessionFactory.setMapperLocations(res);
//
//        return sessionFactory.getObject();
//    }



//    @Override
//    public void run(String... args) throws Exception {
//        test();
//    }

//    public void test(){
//        VirtualObjectData virtualObjectData = new VirtualObjectData("icbms-vo-test",1,1,"test설명");
//        IVirtualObjectDAO.createVirtualObject(virtualObjectData);
//        IVirtualObjectDAO.createVirtualObject(virtualObjectData);
//
//        List<VirtualObjectForDB> virtualObjectForDBList = IVirtualObjectDAO.retrieveVirtualObjectList();
//
//        for(VirtualObjectForDB virtualObjectForDB : virtualObjectForDBList){
//            System.out.println(virtualObjectForDB.toString());
//        }
//
//        VirtualObjectData virtualObjectChangeData = new VirtualObjectData("icbms-vo-test-change",1,1,"test변경설명");
//        IVirtualObjectDAO.updateVirtualObject(3,virtualObjectChangeData);
//
//        for(VirtualObjectForDB virtualObjectForDB : virtualObjectForDBList){
//            System.out.println(virtualObjectForDB.toString());
//        }
//    }
}
