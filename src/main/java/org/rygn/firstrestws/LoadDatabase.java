package org.rygn.firstrestws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {
	
	private Logger log;
	
	public LoadDatabase() {
		this.log = LoggerFactory.getLogger(LoadDatabase.class);
	}

	@Bean
	CommandLineRunner initDatabase(EmployeeRepository repository) {
		return args -> {
			
			this.log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "maçon")));
			
			this.log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "infirmier")));
		};
	}
}
