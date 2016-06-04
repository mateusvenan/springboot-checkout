package br.com.mvenancio.checkout;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class CheckoutRepository {

	private List<ShoppingCart> sales = new ArrayList<>();

	public ShoppingCart add(ShoppingCart shoppingCart) {
		sales.add(shoppingCart);
		return shoppingCart;
	}

	public List<ShoppingCart> findAll() {
		return sales;
	}

}
