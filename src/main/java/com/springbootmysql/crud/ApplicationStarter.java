package com.springbootmysql.crud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
//@Configuration
//@EnableScheduling
//@EnableTransactionManagement
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.springbootmysql.crud"})
//@EnableJpaRepositories(basePackages = {"com.springbootmysql.crud.repository"})
//@EnableJpaAuditing
public class ApplicationStarter {
	private static Logger LOGGER = LogManager.getLogger(ApplicationStarter.class);
	public static boolean findPalindrome(String word){
		int i1=0;
		int i2= word.length()-1;
		while(i2>i1) {
			if(word.charAt(i2)!=word.charAt(i1)) {
				return false;
			}
			i1++;
			i2--;
		}
		
		return true;
		
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
		System.out.println("springboot project configured");
		String input="My name is nitin and i can speak malayalam";
		String[] inputArray=input.split(" ");
		System.out.println("output:");
		for (String word : inputArray) {
			boolean findPalindrome = ApplicationStarter.findPalindrome(word);
			if(findPalindrome) {
				System.out.println(word);
			}
			
		}
	}
	
	
	@Bean
	protected  WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
				.addMapping("/**")
				.allowedMethods("GET", "POST")
				.allowedOrigins("*")
                .allowedHeaders("*");
				//.exposedHeaders("Authorization")
				//.allowCredentials(true)
               // .maxAge(4800L);
				LOGGER.info(registry.toString());
			}
		};
	}

}
