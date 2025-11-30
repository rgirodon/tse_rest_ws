package org.rygn.firstrestws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ProductController {

	@Autowired
	private ProductRepository repository;
  
	public ProductController() {
	}
	
	@GetMapping("/products")
	List<Product> all() {
		return repository.findAll();
	}
	
	@PostMapping("/products")
	Product newEmployee(@RequestBody Product newProduct) {
		
	    return repository.save(newProduct);
	}
	
	@GetMapping("/products/{id}")
	Product one(@PathVariable Long id) {

		Product result = repository.findById(id);
		
		if (result == null)
			throw new ProductNotFoundException(id);
		else
			return result;
	}
	
	@DeleteMapping("/products/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@PutMapping("/products/{id}")
	Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {

		Product foundProduct = repository.findById(id);
		
		if (foundProduct == null)
			throw new ProductNotFoundException(id);
		else {
			foundProduct.setName(newProduct.getName());
			foundProduct.setPrice(newProduct.getPrice());
			foundProduct.setDiscount(newProduct.getDiscount());
			
			return repository.save(foundProduct);
		}
	}
}
