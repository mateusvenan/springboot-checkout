package br.com.mvenancio.checkout.catalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.mvenancio.checkout.BusinessException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public List<Product> findAll() {
		return repository.findAll();
	}

	public Product findBy(String sku) {
		Product product = repository.findBy(sku);
		if (product == null) {
			throw new BusinessException("Product not found", HttpStatus.NOT_FOUND);
		}
		return product;
	}

	public Product findBy(Integer id) {
		Product product = repository.findBy(id);
		if (product == null) {
			throw new BusinessException("Product not found", HttpStatus.NOT_FOUND);
		}
		return product;
	}

	public Product add(Product product) {
		repository.create(product);
		return product;
	}

	public Product update(Product product) {
		repository.update(product);
		return product;
	}

}
