package com.gearup;

import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GearupBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GearupBackendApplication.class, args);
	}
	
	@Bean
	ModelMapper modelMapper() {
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
				.setPropertyCondition(Conditions.isNotNull())
				.setMatchingStrategy(MatchingStrategies.STRICT);
		
		return mapper;
	}

}
