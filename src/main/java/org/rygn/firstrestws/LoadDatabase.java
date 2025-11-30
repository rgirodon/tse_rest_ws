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
	CommandLineRunner initDatabase(EmployeeRepository employeeRepository, ProductRepository productRepository) {
		return args -> {
			
			this.log.info("Preloading " + employeeRepository.save(new Employee(1L, "Bilbo Baggins", "maçon")));
			
			this.log.info("Preloading " + employeeRepository.save(new Employee(2L, "Frodo Baggins", "infirmier")));
			
			this.log.info("Preloading " + productRepository.save(new Product(1L, "TV OLED Philips", 899.9D, null)));
			
			this.log.info("Preloading " + productRepository.save(new Product(2L, "TV OLED Samsung", 999.9D, 10.0D)));
			
			this.log.info("Preloading " + productRepository.save(new Product(3L, "TV OLED LG", 1099.9D, null)));	
			
			this.log.info("Preloading " + productRepository.save(new Product(4L, "TV OLED Panasonic", 1199.9D, 5.0D)));
		};
	}
}
