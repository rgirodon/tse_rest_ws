package org.rygn.firstrestws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
	
	private Map<Long, Product> products;
	
	public ProductRepository() {
		this.products = new HashMap<Long, Product>();
	}

	public List<Product> findAll() {
		
		return this.products.values().stream().toList();
	}

	public Product save(Product newProduct) {
		
		this.products.put(newProduct.getId(), newProduct);
		
		return newProduct;
	}

	public Product findById(Long id) {
		
		return this.products.get(id);
	}

	public void deleteById(Long id) {
		
		this.products.remove(id);
	}

	public Integer count() {
		
		return this.products.size();
	}

	public void delete(Product currentProduct) {
		
		this.deleteById(currentProduct.getId());
	}

}
