package br.com.mvenancio.checkout.offer;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvenancio.checkout.catalog.Product;
import br.com.mvenancio.checkout.catalog.ProductService;

@Service
public class OfferService {

	@Autowired
	private ProductService productService;

	@Autowired
	private OfferRepository repository;

	@PostConstruct
	public void init() {
		repository.create(createAppleTvOffer());
		repository.create(createSuperIpadOffer());
		repository.create(createMackBookOffer());
	}

	private Offer createAppleTvOffer() {
		Product appleTV = productService.findBy("atv");
		return new Offer(appleTV, appleTV, 3);
	}

	private Offer createSuperIpadOffer() {
		Product ipad = productService.findBy("ipd");
		return new Offer(ipad, 4, BigDecimal.valueOf(499.99), OfferType.ABSOLUTE_VALUE);
	}

	private Offer createMackBookOffer() {
		Product mackBook = productService.findBy("mbp");
		Product vga = productService.findBy("vga");
		return new Offer(mackBook, vga, 1);
	}

	public List<Offer> findAll() {
		return repository.findAll();
	}

	public Offer findBy(Integer id) {
		return repository.findBy(id);
	}

	public Offer add(Offer offer) {
		repository.create(offer);
		return offer;
	}

	public Offer update(Offer offer) {
		repository.update(offer);
		return offer;
	}

}
