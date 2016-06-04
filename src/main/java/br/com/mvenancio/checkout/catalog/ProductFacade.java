package br.com.mvenancio.checkout.catalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProductFacade {

	@Autowired
	private ProductService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<Product> get() {
		return service.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Product get(@PathVariable("id") Integer id) {
		return service.findBy(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Product create(@RequestBody Product product) {
		return service.add(product);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Product put(@RequestBody Product product) {
		return service.update(product);
	}
}
