package br.com.mvenancio.checkout.offer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/offers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OfferFacade {

	@Autowired
	private OfferService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<Offer> get() {
		return service.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Offer get(@PathVariable("id") Integer id) {
		return service.findBy(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Offer create(@RequestBody Offer offer) {
		return service.add(offer);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Offer put(@RequestBody Offer offer) {
		return service.update(offer);
	}
}
