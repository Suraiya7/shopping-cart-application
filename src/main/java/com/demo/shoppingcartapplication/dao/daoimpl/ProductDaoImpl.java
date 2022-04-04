package com.demo.shoppingcartapplication.dao.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.shoppingcartapplication.dao.ProductDao;
import com.demo.shoppingcartapplication.entity.Product;
import com.demo.shoppingcartapplication.repository.ProductRepository;

@Component
public class ProductDaoImpl implements ProductDao {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> findAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public Product findByProductId(int productId) {
		// TODO Auto-generated method stub
		return productRepository.findById(productId).get();
	}

	@Override
	public boolean existsByProductId(int productId) {
		// TODO Auto-generated method stub
		return productRepository.existsById(productId);
	}

	@Override
	public Product findProductByName(String productName) {
		// TODO Auto-generated method stub
		return productRepository.findByProductName(productName);
	}

	@Override
	public Product saveAndFlushProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.saveAndFlush(product);
	}

	@Override
	public boolean existsById(int productId) {
		// TODO Auto-generated method stub
		return productRepository.existsById(productId);
	}

}
