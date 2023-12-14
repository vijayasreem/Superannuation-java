package com.lic.epgs;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "SuperAnnuation Service - ePGS", version = "1.0", description = "SuperAnnuation Service - ePGS Module"))
 
/**
 * @author Muruganandam
 *
 */
public class LicEPgsApplication {


	
	public static void main(String[] args) {
		SpringApplication.run(LicEPgsApplication.class, args);
	}

	@Bean("jsonObjectMapper")
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}

	@Bean("restTemplateService")
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
    

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		/**
		 * mapper.getConfiguration().setPropertyCondition(context ->
		 * !(context.getSource() instanceof PersistentCollection));
		 **/
		return mapper;
	}

}
