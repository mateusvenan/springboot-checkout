package br.com.mvenancio.checkout.catalog;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.mvenancio.checkout.BusinessException;

public class ProductRepositoryTest {

	ProductRepository repository;

	@Before
	public void setUp() {
		repository = new ProductRepository();
	}

	@Test
	public void verifyInitialSize() {
		Assert.assertEquals(4, repository.findAll().size());
	}

	@Test
	public void findBySku() {
		Assert.assertEquals("Super Ipad", repository.findBy("ipd").getDescription());
		Assert.assertEquals("Apple TV", repository.findBy("atv").getDescription());
		Assert.assertNull(repository.findBy("xyz"));

	}

	@Test
	public void findById() {
		Assert.assertEquals("VGA adapter", repository.findBy(4).getDescription());
		Assert.assertEquals("Apple TV", repository.findBy(3).getDescription());
		Assert.assertNull(repository.findBy(999));
	}

	@Test
	public void update() {
		String description = "New Description";
		Product product = repository.findBy("vga");
		product.setDescription(description);

		repository.update(product);

		Assert.assertEquals(description, repository.findBy("vga").getDescription());
	}

	@Test(expected = BusinessException.class)
	public void updateUnexistentProduct() {
		String description = "New Description";
		Product product = new Product();
		product.setId(999);
		product.setDescription(description);

		repository.update(product);
	}

}
