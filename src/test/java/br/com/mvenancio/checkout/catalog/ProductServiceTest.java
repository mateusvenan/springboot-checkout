package br.com.mvenancio.checkout.catalog;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import br.com.mvenancio.checkout.BusinessException;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
	@Mock
	ProductRepository repository;

	@InjectMocks
	ProductService service;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findBySkuWhenNotFound() {
		try {
			service.findBy("");
			Assert.fail("Should throw exception");
		} catch (BusinessException e) {
			Assert.assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
		}
	}

	@Test
	public void findBySku() {
		when(repository.findBy(anyString())).thenReturn(new Product());
		service.findBy("");
	}

	@Test
	public void findByIdWhenNotFound() {
		try {
			service.findBy(0);
			Assert.fail("Should throw exception");
		} catch (BusinessException e) {
			Assert.assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
		}
	}

	@Test
	public void findById() {
		when(repository.findBy(anyInt())).thenReturn(new Product());
		service.findBy(1);
	}

}
