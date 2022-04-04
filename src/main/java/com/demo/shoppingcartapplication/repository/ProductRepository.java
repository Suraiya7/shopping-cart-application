package com.demo.shoppingcartapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.shoppingcartapplication.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "select product from product where product_name = ?", nativeQuery = true)
	Product findByProductName(String productName);

}
