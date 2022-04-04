package com.demo.shoppingcartapplication.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;

	private int totalQuantity;

	private float totalAmount;

	@OneToMany(mappedBy = "cartProductPk.cart")
	@Valid
	private List<CartProduct> cartProducts = new ArrayList<>();

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(int cartId, int totalQuantity, float totalAmount, @Valid List<CartProduct> cartProducts) {
		super();
		this.cartId = cartId;
		this.totalQuantity = totalQuantity;
		this.totalAmount = totalAmount;
		this.cartProducts = cartProducts;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartId;
		result = prime * result + ((cartProducts == null) ? 0 : cartProducts.hashCode());
		result = prime * result + Float.floatToIntBits(totalAmount);
		result = prime * result + totalQuantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (cartId != other.cartId)
			return false;
		if (cartProducts == null) {
			if (other.cartProducts != null)
				return false;
		} else if (!cartProducts.equals(other.cartProducts))
			return false;
		if (Float.floatToIntBits(totalAmount) != Float.floatToIntBits(other.totalAmount))
			return false;
		if (totalQuantity != other.totalQuantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", totalQuantity=" + totalQuantity + ", totalAmount=" + totalAmount
				+ ", cartProducts=" + cartProducts + "]";
	}

}
