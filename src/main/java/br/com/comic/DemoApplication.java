package br.com.comic;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.mongodb.client.MongoClient;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class DemoApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper= new ModelMapper();
	    return modelMapper;
	}

	
	
}
