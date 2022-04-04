package com.demo.shoppingcartapplication.dao;

import java.util.List;

import com.demo.shoppingcartapplication.entity.Cart;

public interface CartDao {

	Cart saveCart(Cart cart);

	Cart findByCartId(int cartId);

	Cart saveAndFlushCart(Cart cart);

	boolean existsByCartId(int cartId);

	void deleteByCartId(int cartId);

	List<Cart> findAllCart();

}
