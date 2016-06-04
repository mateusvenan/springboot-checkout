package br.com.mvenancio.checkout.offer;

import java.math.BigDecimal;

import br.com.mvenancio.checkout.catalog.Product;

public class Offer {

	public Offer() {

	}

	public Offer(Product product, Product gift, Integer requiredAmout) {
		setProduct(product);
		setGift(gift);
		setRequiredAmount(requiredAmout);
		setType(OfferType.BUY_AND_WIN);
	}

	public Offer(Product product, Integer requiredAmout, BigDecimal value, OfferType offerType) {
		setProduct(product);
		setRequiredAmount(requiredAmout);
		setType(offerType);
		setValue(value);
	}

	private Integer id;

	private Product product;

	private Product gift;

	private Integer requiredAmount;

	private OfferType type;

	private BigDecimal value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRequiredAmount() {
		return requiredAmount;
	}

	public void setRequiredAmount(Integer requiredAmout) {
		this.requiredAmount = requiredAmout;
	}

	public OfferType getType() {
		return type;
	}

	public void setType(OfferType type) {
		this.type = type;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getGift() {
		return gift;
	}

	public void setGift(Product gift) {
		this.gift = gift;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Offer && ((Offer) obj).getId().equals(this.getId());
	}

}
