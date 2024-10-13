package org.rygn.firstrestws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository repository;
  
	public EmployeeController() {
	}
	
	@GetMapping("/employees")
	List<Employee> all() {
		return repository.findAll();
	}
	
	@PostMapping("/employees")
	Employee newEmployee(@RequestBody Employee newEmployee) {
		
	    return repository.save(newEmployee);
	}
	
	@GetMapping("/employees/{id}")
	Employee one(@PathVariable Long id) {

		Employee result = repository.findById(id);
		
		if (result == null)
			throw new EmployeeNotFoundException(id);
		else
			return result;
	}
	
	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@PutMapping("/employees/{id}")
	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

		Employee foundEmployee = repository.findById(id);
		
		if (foundEmployee == null)
			throw new EmployeeNotFoundException(id);
		else {
			foundEmployee.setName(newEmployee.getName());
			foundEmployee.setRole(newEmployee.getRole());
			
			return repository.save(foundEmployee);
		}
	}
}
