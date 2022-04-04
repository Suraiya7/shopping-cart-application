package com.demo.shoppingcartapplication.dao.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.shoppingcartapplication.dao.CartProductDao;
import com.demo.shoppingcartapplication.entity.CartProduct;
import com.demo.shoppingcartapplication.entity.CartProductPk;
import com.demo.shoppingcartapplication.repository.CartProductRepository;

@Component
public class CartProductDaoImpl implements CartProductDao {

	@Autowired
	CartProductRepository cartProductRepository;

	@Override
	public void deleteCartProduct(CartProduct cartProduct) {
		cartProductRepository.delete(cartProduct);
	}

	@Override
	public CartProduct saveAndFlashCartProduct(CartProduct cartProduct) {
		return cartProductRepository.saveAndFlush(cartProduct);

	}

	@Override
	public boolean existsByCartProductId(CartProductPk cartProductPk) {
		// TODO Auto-generated method stub
		return cartProductRepository.existsById(cartProductPk);
	}

	@Override
	public CartProduct findByCartProductId(CartProductPk cartProductPk) {
		// TODO Auto-generated method stub
		return cartProductRepository.findById(cartProductPk).get();
	}

	@Override
	public CartProduct saveAndFlushCartProduct(CartProduct cartProduct) {
		// TODO Auto-generated method stub
		return cartProductRepository.saveAndFlush(cartProduct);
	}

	@Override
	public CartProduct saveCartProduct(CartProduct cartProduct) {
		// TODO Auto-generated method stub
		return cartProductRepository.save(cartProduct);
	}

}
