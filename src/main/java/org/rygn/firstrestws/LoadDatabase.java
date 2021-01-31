package org.rygn.firstrestws;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(EmployeeRepository repository) {
		return args -> {
			
			Logger logger = Logger.getLogger("logger");
			logger.log(Level.INFO,"Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
			
			logger.log(Level.INFO,"Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
		};
	}
}

