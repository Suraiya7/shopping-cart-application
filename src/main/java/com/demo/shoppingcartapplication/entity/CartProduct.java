package com.demo.shoppingcartapplication.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CartProduct implements Serializable {

	@JsonIgnore
	@EmbeddedId
	private CartProductPk cartProductPk;

	private Integer quantity;

	public CartProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartProduct(CartProductPk cartProductPk, Integer quantity) {
		super();
		this.cartProductPk = cartProductPk;
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartProductPk == null) ? 0 : cartProductPk.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		CartProduct other = (CartProduct) obj;
		if (cartProductPk == null) {
			if (other.cartProductPk != null)
				return false;
		} else if (!cartProductPk.equals(other.cartProductPk))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

	public CartProductPk getCartProductPk() {
		return cartProductPk;
	}

	public void setCartProductPk(CartProductPk cartProductPk) {
		this.cartProductPk = cartProductPk;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartProduct [cartProductPk=" + cartProductPk + ", quantity=" + quantity + "]";
	}

}
