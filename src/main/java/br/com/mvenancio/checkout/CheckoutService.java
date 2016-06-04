package br.com.mvenancio.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvenancio.checkout.offer.Offer;
import br.com.mvenancio.checkout.offer.OfferService;

@Service
public class CheckoutService {

	@Autowired
	private OfferService offerService;

	@Autowired
	private CheckoutRepository repository;

	public ShoppingCart add(ShoppingCart shoppingCart) {
		calculatePrices(shoppingCart);
		repository.add(shoppingCart);
		return shoppingCart;
	}

	public List<ShoppingCart> findAll() {
		return repository.findAll();
	}

	void calculatePrices(ShoppingCart shoppingCart) {
		List<Offer> offers = offerService.findAll();

		IntStream.range(0, shoppingCart.getItems().size()).forEach(ix -> {
			ItemCart item = shoppingCart.getItems().get(ix);

			if (item.getUnitValue() == null) {
				item.setUnitValue(item.getProduct().getPrice());
			}

			List<Offer> productOffers = offers.stream().filter(o -> {
				return o.getProduct().equals(item.getProduct()) && item.getAmount().equals(o.getRequiredAmount());
			}).collect(Collectors.toList());

			BigDecimal amount = BigDecimal.valueOf(item.getAmount());

			if (productOffers.isEmpty()) {
				fillDiscountAndTotal(item, BigDecimal.ZERO, item.getUnitValue().multiply(amount));
			} else {
				productOffers.forEach(offer -> {
					switch (offer.getType()) {
					case BUY_AND_WIN:
						calculateBuyAndWinDiscounts(shoppingCart, item, offer, amount);
						break;
					case ABSOLUTE_VALUE:
						calculateAbsoluteValueDiscounts(item, offer, amount);
						break;
					case DISCOUNT_PERCENTAGE:
						calculatePercentageDiscount(item, offer, amount);
						break;
					}
				});
			}

			shoppingCart.addTotalValue(item.getTotalValue());
			shoppingCart.addDiscountValue(item.getDiscountValue());
		});
	}

	private void calculateBuyAndWinDiscounts(ShoppingCart shoppingCart, ItemCart item, Offer offer, BigDecimal amount) {
		if (item.getProduct().equals(offer.getGift())) {
			fillDiscountAndTotal(item, item.getProduct().getPrice(),
					item.getUnitValue().multiply(amount.subtract(BigDecimal.ONE)));
		} else {
			fillDiscountAndTotal(item, BigDecimal.ZERO, item.getUnitValue().multiply(amount));

			ItemCart gift = createGiftItemCart(offer);
			shoppingCart.addDiscountValue(gift.getDiscountValue());
			shoppingCart.getItems().add(gift);
		}
	}

	private void calculateAbsoluteValueDiscounts(ItemCart item, Offer offer, BigDecimal amount) {
		BigDecimal diff = item.getProduct().getPrice().subtract(offer.getValue());
		item.setUnitValue(offer.getValue());
		fillDiscountAndTotal(item, diff.multiply(amount), item.getUnitValue().multiply(amount));
	}

	private void calculatePercentageDiscount(ItemCart item, Offer offer, BigDecimal amount) {
		BigDecimal decimal = offer.getValue().divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
		BigDecimal unitValue = item.getProduct().getPrice().multiply(decimal);
		BigDecimal difference = item.getProduct().getPrice().subtract(unitValue);

		item.setUnitValue(unitValue);
		fillDiscountAndTotal(item, difference.multiply(amount), item.getUnitValue().multiply(amount));
	}

	private void fillDiscountAndTotal(ItemCart item, BigDecimal discountValue, BigDecimal totalValue) {
		item.setDiscountValue(discountValue);
		item.setTotalValue(totalValue);
	}

	private ItemCart createGiftItemCart(Offer offer) {
		BigDecimal price = offer.getGift().getPrice();

		ItemCart gift = new ItemCart();
		gift.setAmount(1);
		gift.setProduct(offer.getGift());
		gift.setUnitValue(price);
		gift.setDiscountValue(price);
		gift.setTotalValue(BigDecimal.ZERO);
		return gift;
	}

}
