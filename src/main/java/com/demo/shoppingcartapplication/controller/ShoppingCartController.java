package com.demo.shoppingcartapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.shoppingcartapplication.dto.CartDto;
import com.demo.shoppingcartapplication.entity.Cart;
import com.demo.shoppingcartapplication.entity.Product;
import com.demo.shoppingcartapplication.exception.ApplicationException;
import com.demo.shoppingcartapplication.exception.exceptionhandler.ResponseMsg;
import com.demo.shoppingcartapplication.model.ProductModel;
import com.demo.shoppingcartapplication.service.ShoppingCartService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@RequestMapping(value = "/addProductInCart/{productId}/{userId}/{quantity}", method = RequestMethod.POST)
	public ResponseEntity<ResponseMsg> addProductInCart(@PathVariable int productId, @PathVariable int userId,
			@PathVariable int quantity) throws ApplicationException {
		ResponseMsg responseMsg = new ResponseMsg();

		String status = shoppingCartService.addProductInCart(productId, userId, quantity);
		responseMsg.setMessage(status);
		responseMsg.setStatus("Success");
		return new ResponseEntity<ResponseMsg>(responseMsg, HttpStatus.OK);

	}

	@RequestMapping(value = "/viewAllProducts/{userId}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductModel>> getAllProducts(@PathVariable int userId) {
		List<ProductModel> productModels = shoppingCartService.getAllProducts(userId);
		return new ResponseEntity<List<ProductModel>>(productModels, HttpStatus.OK);

	}

	@RequestMapping(value = "/getProductById/{productId}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProductById(@PathVariable int productId) throws ApplicationException {
		Product product = shoppingCartService.getProductById(productId);
		return new ResponseEntity<Product>(product, HttpStatus.OK);

	}

	@RequestMapping(value = "/getProductByName/{productName}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProductByName(@PathVariable String productName) throws ApplicationException {
		Product product = shoppingCartService.getProductByName(productName);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@RequestMapping(value = "/getProductByCatagory/{catagory}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductByCatagory(@PathVariable String catagory)
			throws ApplicationException {
		List<Product> products = shoppingCartService.getProductByCatagory(catagory);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

	}

	@RequestMapping(value = "/searchProduct/{variable}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductModel>> getProductByAny(@PathVariable String variable)
			throws ApplicationException {

		List<ProductModel> productModel = shoppingCartService.getProductByAny(variable);
		return new ResponseEntity<List<ProductModel>>(productModel, HttpStatus.OK);

	}

	@RequestMapping(value = "/viewCartById/{userId}", method = RequestMethod.GET)
	public ResponseEntity<CartDto> viewCartById(@PathVariable int userId) throws ApplicationException {
		CartDto cartDto = shoppingCartService.viewCartById(userId);
		return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);

	}

	@RequestMapping(value = "/removeProductById/{productId}/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseMsg> removeProductById(@PathVariable int productId, @PathVariable int userId)
			throws ApplicationException {
		ResponseMsg responseMsg = new ResponseMsg();
		String status = shoppingCartService.removeProductById(productId, userId);
		responseMsg.setMessage(status);
		responseMsg.setStatus("Success");
		return new ResponseEntity<ResponseMsg>(responseMsg, HttpStatus.OK);
	}

	@RequestMapping(value = "/removeAllProduct/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseMsg> removeAllProduct(@PathVariable int userId) throws ApplicationException {
		ResponseMsg responseMsg = new ResponseMsg();

		String status = shoppingCartService.removeAllProduct(userId);
		responseMsg.setMessage(status);
		responseMsg.setStatus("Success");
		return new ResponseEntity<ResponseMsg>(responseMsg, HttpStatus.OK);

	}

	@RequestMapping(value = "/updateQuantity/{userId}/{productId}/{quantity}", method = RequestMethod.PUT)
	public ResponseEntity<Cart> updateQuantity(@PathVariable int userId, @PathVariable int productId,
			@PathVariable int quantity) throws ApplicationException {
		Cart cartProductDto = shoppingCartService.updateQuantity(userId, productId, quantity);
		return new ResponseEntity<Cart>(cartProductDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/increaseQuantity/{userId}/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseMsg> increaseQuantity(@PathVariable int userId, @PathVariable int productId)
			throws ApplicationException {
		String status = shoppingCartService.increaseQuantity(userId, productId);
		ResponseMsg responseMsg = new ResponseMsg();
		responseMsg.setStatus("Success");
		responseMsg.setMessage(status);
		return new ResponseEntity<ResponseMsg>(responseMsg, HttpStatus.OK);

	}

	@RequestMapping(value = "/decreaseQuantity/{userId}/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseMsg> decreaseQuantity(@PathVariable int userId, @PathVariable int productId)
			throws ApplicationException {
		String status = shoppingCartService.decreaseQuantity(userId, productId);
		ResponseMsg responseMsg = new ResponseMsg();
		responseMsg.setStatus("Success");
		responseMsg.setMessage(status);
		return new ResponseEntity<ResponseMsg>(responseMsg, HttpStatus.OK);
	}

}
