package com.pineone.icbms.so.serviceprocessor;

import com.pineone.icbms.so.util.spring.beans.CustomBeanNameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Arrays;

@SpringBootApplication
public class SoProcessorApplication implements CommandLineRunner {
    /**
     * log
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * run application.<BR/>
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        System.out.println("*user.dir: " + System.getProperty("user.dir"));
//		SpringApplication.run(SoProcessorApplication.class, args);
//        SpringApplication app = new SpringApplication(SoProcessorApplication.class);
//        app.run(args);

        new SpringApplicationBuilder(SoProcessorApplication.class)
                .beanNameGenerator(new CustomBeanNameGenerator())
                .run(args);

    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        log.debug("args: {}", Arrays.stream(args).toArray());
//        ProcessorRouter.getInstance().run(args);
    }
}
