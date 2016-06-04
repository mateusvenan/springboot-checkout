package br.com.mvenancio.checkout.catalog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import br.com.mvenancio.checkout.BusinessException;

@Repository
public class ProductRepository {

	public int sequence = 0;
	private List<Product> products = new ArrayList<>();

	public ProductRepository() {
		create(new Product("ipd", "Super Ipad", BigDecimal.valueOf(549.99)));
		create(new Product("mbp", "MacBook Pro", BigDecimal.valueOf(1399.99)));
		create(new Product("atv", "Apple TV", BigDecimal.valueOf(109.50)));
		create(new Product("vga", "VGA adapter", BigDecimal.valueOf(30.00)));
	}

	public List<Product> findAll() {
		return products;
	}

	public Product findBy(String sku) {
		return products.stream().filter(p -> p.getSku().equals(sku)).findFirst().orElse(null);
	}

	public Product findBy(Integer id) {
		return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
	}

	public void create(Product product) {
		product.setId(++sequence);
		products.add(product);
	}

	public void update(Product product) {
		Product actualProduct = products.stream().filter(o -> o.getSku().equals(product.getSku())).findFirst()
				.orElse(null);
		if (actualProduct == null) {
			throw new BusinessException("Product not fount", HttpStatus.NOT_FOUND);
		}
		products.set(products.indexOf(product), product);
	}
}
