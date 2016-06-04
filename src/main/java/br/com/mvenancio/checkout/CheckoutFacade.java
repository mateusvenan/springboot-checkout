package br.com.mvenancio.checkout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/checkout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CheckoutFacade {

	@Autowired
	private CheckoutService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<ShoppingCart> get() {
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ShoppingCart post(@RequestBody ShoppingCart cart) {
		return service.add(cart);
	}

}
