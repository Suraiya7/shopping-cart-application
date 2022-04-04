package com.demo.shoppingcartapplication.dao.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.shoppingcartapplication.dao.CartDao;
import com.demo.shoppingcartapplication.entity.Cart;
import com.demo.shoppingcartapplication.repository.CartRepository;

@Component
public class CartDaoImpl implements CartDao {

	@Autowired
	CartRepository cartRepoitory;

	@Override
	public Cart saveCart(Cart cart) {
		return cartRepoitory.save(cart);
	}

	@Override
	public Cart findByCartId(int cartId) {
		// TODO Auto-generated method stub
		return cartRepoitory.findById(cartId).get();
	}

	@Override
	public Cart saveAndFlushCart(Cart cart) {
		// TODO Auto-generated method stub
		return cartRepoitory.saveAndFlush(cart);
	}

	@Override
	public boolean existsByCartId(int cartId) {
		// TODO Auto-generated method stub
		return cartRepoitory.existsById(cartId);
	}

	@Override
	public void deleteByCartId(int cartId) {
		cartRepoitory.deleteById(cartId);
	}

	@Override
	public List<Cart> findAllCart() {
		// TODO Auto-generated method stub
		return cartRepoitory.findAll();
	}

}
