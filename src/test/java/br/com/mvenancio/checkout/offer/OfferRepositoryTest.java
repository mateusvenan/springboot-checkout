package br.com.mvenancio.checkout.offer;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.mvenancio.checkout.catalog.Product;

public class OfferRepositoryTest {
	OfferRepository repository;

	@Before
	public void setUp() {
		repository = new OfferRepository();
	}

	@Test
	public void verifyInitialSize() {
		Assert.assertEquals(0, repository.findAll().size());
	}

	@Test
	public void create() {
		repository.create(new Offer());
		List<Offer> allOffers = repository.findAll();
		Assert.assertEquals(1, allOffers.size());
		Assert.assertEquals(Integer.valueOf(1), allOffers.get(0).getId());
	}

	@Test
	public void findById() {
		Product product1 = new Product("aaa");
		Product product2 = new Product("bbb");
		repository.create(new Offer(product1, null, null));
		repository.create(new Offer(product2, null, null));

		Assert.assertEquals(product2.getSku(), repository.findBy(2).getProduct().getSku());
		Assert.assertEquals(product1.getSku(), repository.findBy(1).getProduct().getSku());
	}

	@Test
	public void update() {
		Product product1 = new Product("aaa");
		Product product2 = new Product("bbb");

		repository.create(new Offer(product1, null, null));
		Assert.assertEquals(product1.getSku(), repository.findBy(1).getProduct().getSku());

		Offer offer = new Offer(product2, null, null);
		offer.setId(1);
		repository.update(offer);

		Assert.assertEquals(product2.getSku(), repository.findBy(1).getProduct().getSku());

	}

}
