package com.demo.shoppingcartapplication.dao;

import java.util.List;

import com.demo.shoppingcartapplication.entity.Product;

public interface ProductDao {

	List<Product> findAllProduct();

	boolean existsByProductId(int productId);

	Product findByProductId(int productId);

	Product findProductByName(String productName);

	Product saveAndFlushProduct(Product product);

	boolean existsById(int productId);

}
