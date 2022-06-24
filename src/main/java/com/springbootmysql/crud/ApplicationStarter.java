package com.springbootmysql.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication(scanBasePackages = {"com.springbootmysql.crud"})
@Configuration
@EnableScheduling
@EnableTransactionManagement
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.springbootmysql.crud"})
@EnableJpaRepositories(basePackages = {"com.springbootmysql.crud.repository"})
@EnableJpaAuditing
public class ApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
		System.out.println("springboot project configured");
	}

}
