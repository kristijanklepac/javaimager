package com.chrisvzimager.chrisvzimager;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import com.chrisvzimager.chrisvzimager.services.StorageProperties;


/**
 * @author Kristijan Klepač
 * @email kristijan.klepac@gmail.com
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ChrisvzimagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChrisvzimagerApplication.class, args);
	}
	
	@Configuration
	@EnableWebMvc
	public class WebConfig implements WebMvcConfigurer {

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {

	        registry.addMapping("/api/**")
	            .allowedOrigins("http://localhost:8080")
	            /*.allowedMethods("PUT", "DELETE")*/
	            .allowedHeaders("header1", "header2", "header3")
	            .exposedHeaders("header1", "header2")
	            .allowCredentials(true).maxAge(3600);

	        // Add more mappings...
	    }
	}
	
}
