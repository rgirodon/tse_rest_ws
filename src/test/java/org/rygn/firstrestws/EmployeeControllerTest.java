package org.rygn.firstrestws;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;

import org.hamcrest.Matchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void testAll() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders
				.get("/employees")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.length()", is(2)))
			    .andExpect(jsonPath("$[?(@.name == 'Bilbo Baggins')].role", Matchers.contains("maçon")));
	}
	
	@Test
	public void testOne() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders
				.get("/employees/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.name", is("Bilbo Baggins")))
			    .andExpect(jsonPath("$.role", is("maçon")));
	}
	
	@Test
	public void testNewEmployee() throws Exception {
		
		Employee employee = new Employee(3L, "Rémy Girodon", "developer");
		
		ObjectMapper mapper = new ObjectMapper();
        byte[] employeeAsBytes = mapper.writeValueAsBytes(employee);
		
		mvc.perform(
				MockMvcRequestBuilders
				.post("/employees")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(employeeAsBytes))
				.andExpect(status().isOk());
		
		assertEquals(3, employeeRepository.count());
		
		Collection<Employee> employees = employeeRepository.findAll();
		
		boolean found = false;
		
		for (Employee currentEmployee : employees) {
			
			if (currentEmployee.getName().equals("Rémy Girodon")) {
				
				assertEquals("developer", currentEmployee.getRole());
				
				found = true;
				
				employeeRepository.delete(currentEmployee);
			}
		}
		
		assertTrue(found);
	}
	
	@Test
	public void testDeleteEmployee() throws Exception {
		
		Employee employee = new Employee(3L, "Rémy Girodon", "developer");
		
		employeeRepository.save(employee);
		
		Collection<Employee> employees = employeeRepository.findAll();
		
		Long id = 0L;
		
		for (Employee currentEmployee : employees) {
			
			if (currentEmployee.getName().equals("Rémy Girodon")) {
				
				id = currentEmployee.getId();
			}
		}
		
		mvc.perform(
				MockMvcRequestBuilders
				.delete("/employees/" + id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		assertEquals(2, employeeRepository.count());
	}
	
	@Test
	public void testReplaceEmployee() throws Exception {
		
		Employee employee = new Employee(1L, "Bilbo Baggins", "vendor");
		
		ObjectMapper mapper = new ObjectMapper();
        byte[] employeeAsBytes = mapper.writeValueAsBytes(employee);
        
        mvc.perform(
				MockMvcRequestBuilders
				.put("/employees/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(employeeAsBytes))
				.andExpect(status().isOk());
        
        employee = employeeRepository.findById(1L);
        
        if (employee == null) {
        	
        	fail("Employee not found");
        }
        
        assertEquals("vendor", employee.getRole());
        
        employee.setRole("maçon");

        employeeRepository.save(employee);
	}
}
