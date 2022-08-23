package com.springbootmysql.crud.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
	private static Logger LOGGER = LogManager.getLogger(CorsConfig.class);
    /**
     * Overriding the CORS configuration to exposed required header for ussd to work
     *
     * @param registry CorsRegistry
     */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST")
                 .allowedHeaders("*");
               // .exposedHeaders("Authorization")
                //.allowCredentials(true)
                //.maxAge(4800L);
                
        LOGGER.info(registry);
    }
}