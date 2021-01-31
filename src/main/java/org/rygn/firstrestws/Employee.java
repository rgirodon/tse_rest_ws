package org.rygn.firstrestws;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Employee {

	private @Id @GeneratedValue Long id;

	private String name;
  
	private String role;
  
	Employee() {		
	}

	Employee(String name, String role) {
		this.name = name;
		this.role = role;
	}

	public String getName() {
		return this.name;
	}

	public String getRole() {
		return this.role;
	}

	public void setName(String name2) {
		this.name = name2;
		
	}

	public void setRole(String role2) {
		this.role = role2;
	}
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
