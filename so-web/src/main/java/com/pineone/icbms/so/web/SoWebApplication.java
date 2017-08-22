package com.pineone.icbms.so.web;

import com.pineone.icbms.so.util.spring.beans.CustomBeanNameGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = {
        "com.kastkode.springsandwich.filter" ,
        "com.pineone.icbms.so.interfaces.database.dao",
//        "com.pineone.icbms.so.web" ,
//        "com.pineone.icbms.so.interfaces" ,
//        "com.pineone.icbms.so.serviceprocessor" ,
        "com.pineone.icbms.so"
}) //package list에서 "com.pineone.icbms.so.serviceprocessor"를 제거하여 kafka 기능을 비활성화.
public class SoWebApplication /* extends SpringBootServletInitializer for tomcat binding*/ { // for jetty binding
    /**
     * for tomcat binding.<BR/>
     *
     * @param builder SpringApplicationBuilder
     * @return SpringApplicationBuilder
     */
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(SoWebApplication.class);
//    }

    /**
     * run application.<BR/>
     *
     * @param args arguments
     */
    public static void main(String[] args) {
//        System.out.println("user.dir: " + System.getProperty("user.dir"));
//		SpringApplication.run(SoWebApplication.class, args);

        // for same name java class, create another bean name
        new SpringApplicationBuilder(SoWebApplication.class)
                .beanNameGenerator(new CustomBeanNameGenerator())
                .run(args);
    }
}
