package com.springbootmysql.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
//@Configuration
//@EnableScheduling
//@EnableTransactionManagement
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.springbootmysql.crud"})
//@EnableJpaRepositories(basePackages = {"com.springbootmysql.crud.repository"})
//@EnableJpaAuditing
public class ApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
		System.out.println("springboot project configured");
	}

}
