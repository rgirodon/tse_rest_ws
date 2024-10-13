package org.rygn.firstrestws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {
	
	private Map<Long, Employee> employees;
	
	public EmployeeRepository() {
		this.employees = new HashMap<Long, Employee>();
	}

	public List<Employee> findAll() {
		
		return this.employees.values().stream().toList();
	}

	public Employee save(Employee newEmployee) {
		
		this.employees.put(newEmployee.getId(), newEmployee);
		
		return newEmployee;
	}

	public Employee findById(Long id) {
		
		return this.employees.get(id);
	}

	public void deleteById(Long id) {
		
		this.employees.remove(id);
	}

	public Integer count() {
		
		return this.employees.size();
	}

	public void delete(Employee currentEmployee) {
		
		this.deleteById(currentEmployee.getId());
	}

}
