package com.pineone.icbms.so.schedule;

import com.pineone.icbms.so.interfaces.database.SoIfDbApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = {
		"com.pineone.icbms.so.interfaces.database"})
//@Import({SoIfDbApplication.class})
@EnableScheduling
public class SoSchedulerApplication {
	/**
	 * application main
	 * @param args
	 */
	public static void main(String[] args) {
//		SpringApplication.run(SoSchedulerApplication.class, args);
		SpringApplication app = new SpringApplication(SoSchedulerApplication.class);
		app.run(args);
	}
}
