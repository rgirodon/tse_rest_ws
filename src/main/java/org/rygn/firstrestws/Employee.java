package org.rygn.firstrestws;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Employee {

	@Id 
	@GeneratedValue
	private Long id;
  
	private String name;
  
	private String role;
  
	Employee() {		
	}

	Employee(String name, String role) {
		this.name = name;
		this.role = role;
	}
}
