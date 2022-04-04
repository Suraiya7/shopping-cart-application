package com.demo.shoppingcartapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.shoppingcartapplication.entity.CartProduct;
import com.demo.shoppingcartapplication.entity.CartProductPk;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductPk> {

}
