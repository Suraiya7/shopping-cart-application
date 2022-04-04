package com.demo.shoppingcartapplication.service;

import java.util.List;

import com.demo.shoppingcartapplication.dto.CartDto;
import com.demo.shoppingcartapplication.entity.Cart;
import com.demo.shoppingcartapplication.entity.Product;
import com.demo.shoppingcartapplication.exception.ServiceException;
import com.demo.shoppingcartapplication.model.ProductModel;

public interface ShoppingCartService {

	Product getProductById(int productId) throws ServiceException;

	Product getProductByName(String productName) throws ServiceException;

	String addProductInCart(int productId, int userId, int quantity) throws ServiceException;

	List<ProductModel> getAllProducts(int userId);

	List<Product> getProductByCatagory(String catagory) throws ServiceException;

	String removeProductById(int productId, int cartId) throws ServiceException;

	String removeAllProduct(int cartId) throws ServiceException;

	Cart updateQuantity(int userId, int productId, int quantity) throws ServiceException;

	List<ProductModel> getProductByAny(String variable) throws ServiceException;

	CartDto viewCartById(int userId);

	String increaseQuantity(int userId, int productId) throws ServiceException;

	String decreaseQuantity(int userId, int productId) throws ServiceException;

}
