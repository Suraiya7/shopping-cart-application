package com.demo.shoppingcartapplication.dao;

import com.demo.shoppingcartapplication.entity.CartProduct;
import com.demo.shoppingcartapplication.entity.CartProductPk;

public interface CartProductDao {

	void deleteCartProduct(CartProduct cartProduct);

	CartProduct saveAndFlashCartProduct(CartProduct cartProduct);

	boolean existsByCartProductId(CartProductPk cartProductPk);

	CartProduct findByCartProductId(CartProductPk cartProductPk);

	CartProduct saveAndFlushCartProduct(CartProduct cartProduct);

	CartProduct saveCartProduct(CartProduct cartProduct);

}
