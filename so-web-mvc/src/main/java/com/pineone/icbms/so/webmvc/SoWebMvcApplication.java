package com.pineone.icbms.so.webmvc;

import com.pineone.icbms.so.util.spring.beans.CustomBeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableWebMvc
@EnableAutoConfiguration
//@PropertySource("classpath:application.yml")
@SpringBootApplication(scanBasePackages = {
        "com.pineone.icbms.so.webmvc" ,
        "com.pineone.icbms.so"
})
public class SoWebMvcApplication extends SpringBootServletInitializer { //for tomcat binding
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SoWebMvcApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SoWebMvcApplication.class, args);
    }

//    @Bean
//    @Description("Thymeleaf template resolver serving HTML 5")
//    public ServletContextTemplateResolver templateResolver() {
//        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
//        templateResolver.setCacheable(false);
//        templateResolver.setTemplateMode("HTML5");
//        templateResolver.setCharacterEncoding("UTF-8");
//        templateResolver.setPrefix("classpath:/WEN-INF/jsp");
//        templateResolver.setSuffix(".jsp");
//
//        return templateResolver;
//    }
//
//    @Bean
//    @Description("Thymeleaf template engine with Spring integration")
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        //templateEngine.addDialect(new SpringSecurityDialect());
//        templateEngine.addDialect(new LayoutDialect(new GroupingStrategy()));
//        templateEngine.setTemplateResolver(templateResolver());
//
//        return templateEngine;
//    }
//
//    @Bean
//    @Description("Thymeleaf view resolver")
//    public ViewResolver viewResolver() {
//
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine());
//        viewResolver.setCharacterEncoding("UTF-8");
//        viewResolver.setCache(false);
//        viewResolver.setOrder(1);
//
//        return viewResolver;
//    }
}
