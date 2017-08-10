package com.pineone.icbms.so.interfaces.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by jonghee on 2017-06-20.
 */
@Configuration
@MapperScan(value="com.pineone.icbms.so.interfaces.database.dao", sqlSessionFactoryRef="sqlSessionFactory")
@EnableTransactionManagement
public class DatabaseConfig {

    @Bean(name = "datasource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory SqlSessionFactory(@Qualifier("datasource") DataSource dataSource
            , ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        // accur Could not resolve type alias in running jar
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);

        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(
                applicationContext.getResources("classpath:META-INF/mappers/*.xml")
        );
        // configuration 은 xml 파일로 처리
        sqlSessionFactoryBean.setConfigLocation(
                applicationContext.getResource("classpath:META-INF/mybatis-config.xml")
        );
//        sqlSessionFactoryBean.setConfigurationProperties(mybatisProperties());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.pineone.icbms.so.interfaces.database.model");

        return sqlSessionFactoryBean.getObject();
    }

    private Properties mybatisProperties() {
        Properties properties = new Properties();
        properties.put("mapUnderscoreToCamelCase", "true");
        properties.put("lazyLoadingEnabled", "true");
        properties.put("aggressiveLazyLoading", "true");
        properties.put("defaultStatementTimeout", 30);
        properties.put("defaultFetchSize", 100);

        return properties;
    }

    @Bean(name = "sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {

        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

