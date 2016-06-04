package br.com.mvenancio.checkout.offer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import br.com.mvenancio.checkout.BusinessException;
import br.com.mvenancio.checkout.catalog.Product;

@Repository
public class OfferRepository {

	public int sequence = 0;

	private List<Offer> offers = new ArrayList<>();

	public List<Offer> findAll() {
		return offers;
	}

	public Offer findBy(Integer id) {
		return offers.stream().filter(o -> o.getId().equals(id)).findFirst().orElse(null);
	}

	public List<Offer> findBy(Product product) {
		return offers.stream().filter(o -> o.getProduct().getId().equals(product.getId())).collect(Collectors.toList());
	}

	public void create(Offer offer) {
		offer.setId(++sequence);
		offers.add(offer);
	}

	public void update(Offer offer) {
		Offer actualOffer = offers.stream().filter(o -> o.getId().equals(offer.getId())).findFirst().orElse(null);
		if (actualOffer == null) {
			throw new BusinessException("Offer not found", HttpStatus.NOT_FOUND);
		}
		offers.set(offers.indexOf(offer), offer);
	}

}
