package br.com.mvenancio.checkout.offer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import br.com.mvenancio.checkout.BusinessException;

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceTest {

	@Mock
	OfferRepository repository;

	@InjectMocks
	OfferService service;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindByWhenOfferNotExist() {
		try {
			service.findBy(0);
			Assert.fail("should throw exception");
		} catch (BusinessException e) {
			Assert.assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
		}
	}

	@Test
	public void testFindBy() {
		Mockito.when(repository.findBy(Mockito.anyInt())).thenReturn(new Offer());
		service.findBy(0);
	}

}
