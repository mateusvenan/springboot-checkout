package br.com.mvenancio.checkout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.mvenancio.checkout.catalog.Product;
import br.com.mvenancio.checkout.offer.Offer;
import br.com.mvenancio.checkout.offer.OfferService;
import br.com.mvenancio.checkout.offer.OfferType;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutServiceTest {

	@InjectMocks
	CheckoutService service;

	@Mock
	OfferService offerService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void calculatePricesWhenBuyAndWinOffer() {
		BigDecimal price = BigDecimal.valueOf(109.50);
		Product appleTv = createProduct(1, "atv", "Apple TV", price);
		Offer offer = new Offer(appleTv, appleTv, 3);
		Mockito.when(offerService.findAll()).thenReturn(Arrays.asList(offer));

		ItemCart item = new ItemCart();
		item.setProduct(appleTv);
		item.setAmount(3);

		ShoppingCart cart = new ShoppingCart();
		cart.setItems(Arrays.asList(item));
		service.calculatePrices(cart);

		Assert.assertEquals(price.multiply(BigDecimal.valueOf(2)), cart.getTotalValue());
		Assert.assertEquals(price, cart.getDiscountValue());
	}

	@Test
	public void calculatePricesWhenBuyAndWinOtherOffer() {
		BigDecimal price = BigDecimal.valueOf(1399.99);
		BigDecimal vgaPrice = BigDecimal.valueOf(30.00);
		Product macBook = createProduct(1, "mbp", "MackBook", price);
		Product vga = createProduct(2, "vga", "VGA Adapter", vgaPrice);
		Offer offer = new Offer(macBook, vga, 1);
		Mockito.when(offerService.findAll()).thenReturn(Arrays.asList(offer));

		ItemCart item = new ItemCart();
		item.setProduct(macBook);
		item.setAmount(1);
		List<ItemCart> items = new ArrayList<>();
		items.add(item);
		ShoppingCart cart = new ShoppingCart();
		cart.setItems(items);
		service.calculatePrices(cart);

		Assert.assertEquals(price, cart.getTotalValue());
		Assert.assertEquals(vgaPrice, cart.getDiscountValue());
		Assert.assertEquals(2, cart.getItems().size());
	}

	@Test
	public void calculatePricesWhenAbsolutePriceDiscount() {
		BigDecimal price = BigDecimal.valueOf(549.99);
		BigDecimal offerPrice = BigDecimal.valueOf(499.99);
		Product ipad = new Product("ipd", "Super Ipad", price);
		ipad.setId(1);
		Offer offer = new Offer(ipad, 4, offerPrice, OfferType.ABSOLUTE_VALUE);
		Mockito.when(offerService.findAll()).thenReturn(Arrays.asList(offer));

		ItemCart item = new ItemCart();
		item.setProduct(ipad);
		item.setAmount(4);

		ShoppingCart cart = new ShoppingCart();
		cart.setItems(Arrays.asList(item));
		service.calculatePrices(cart);

		BigDecimal amount = BigDecimal.valueOf(4);
		BigDecimal expectedDiscount = price.multiply(amount).subtract(offerPrice.multiply(amount));

		Assert.assertEquals(offerPrice.multiply(amount), cart.getTotalValue());
		Assert.assertEquals(expectedDiscount, cart.getDiscountValue());
	}

	@Test
	public void calculatePricesWhenAbsolutePriceDiscountNotReached() {
		BigDecimal price = BigDecimal.valueOf(549.99);
		BigDecimal offerPrice = BigDecimal.valueOf(499.99);
		Product ipad = new Product("ipd", "Super Ipad", price);
		ipad.setId(1);
		Offer offer = new Offer(ipad, 4, offerPrice, OfferType.ABSOLUTE_VALUE);
		Mockito.when(offerService.findAll()).thenReturn(Arrays.asList(offer));

		ItemCart item = new ItemCart();
		item.setProduct(ipad);
		item.setAmount(3);

		ShoppingCart cart = new ShoppingCart();
		cart.setItems(Arrays.asList(item));
		service.calculatePrices(cart);

		BigDecimal amount = BigDecimal.valueOf(3);

		Assert.assertEquals(price.multiply(amount), cart.getTotalValue());
		Assert.assertEquals(BigDecimal.ZERO, cart.getDiscountValue());
	}

	private Product createProduct(int id, String sku, String description, BigDecimal price) {
		Product appleTv = new Product(sku, description, price);
		appleTv.setId(id);
		return appleTv;
	}

}