package br.com.mvenancio.checkout;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCart {

	private List<ItemCart> items;

	private BigDecimal totalValue = BigDecimal.ZERO;
	private BigDecimal discountValue = BigDecimal.ZERO;

	public List<ItemCart> getItems() {
		return items;
	}

	public void setItems(List<ItemCart> items) {
		this.items = items;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public void addTotalValue(BigDecimal totalValue) {
		this.totalValue = this.totalValue.add(totalValue);
	}

	public BigDecimal getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}
	public void addDiscountValue(BigDecimal discountValue) {
		this.discountValue = this.discountValue.add(discountValue);
	}

}
