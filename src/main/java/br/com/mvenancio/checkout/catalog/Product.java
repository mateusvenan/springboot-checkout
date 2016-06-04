package br.com.mvenancio.checkout.catalog;

import java.math.BigDecimal;

public class Product {

	public Product() {
	}

	public Product(String sku) {
		setSku(sku);
	}

	public Product(String sku, String description, BigDecimal price) {
		setSku(sku);
		setDescription(description);
		setPrice(price);
	}

	private Integer id;

	private String sku;

	private String description;

	private BigDecimal price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Product && ((Product) obj).getId().equals(this.id);
	}

}
