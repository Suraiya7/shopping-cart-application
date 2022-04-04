package com.demo.shoppingcartapplication.service.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.shoppingcartapplication.dao.ApparalDao;
import com.demo.shoppingcartapplication.dao.BookDao;
import com.demo.shoppingcartapplication.dao.CartDao;
import com.demo.shoppingcartapplication.dao.CartProductDao;
import com.demo.shoppingcartapplication.dao.ProductDao;
import com.demo.shoppingcartapplication.dao.UserDao;
import com.demo.shoppingcartapplication.dto.CartDto;
import com.demo.shoppingcartapplication.dto.ProductDto;
import com.demo.shoppingcartapplication.entity.Apparal;
import com.demo.shoppingcartapplication.entity.Book;
import com.demo.shoppingcartapplication.entity.Cart;
import com.demo.shoppingcartapplication.entity.CartProduct;
import com.demo.shoppingcartapplication.entity.CartProductPk;
import com.demo.shoppingcartapplication.entity.Product;
import com.demo.shoppingcartapplication.entity.User;
import com.demo.shoppingcartapplication.exception.NoProductAvailableInCart;
import com.demo.shoppingcartapplication.exception.ProductDoesNotExist;
import com.demo.shoppingcartapplication.exception.ProductQuantityExhausted;
import com.demo.shoppingcartapplication.exception.ServiceException;
import com.demo.shoppingcartapplication.exception.UserDoesNotExist;
import com.demo.shoppingcartapplication.exception.UserDoesNotExists;
import com.demo.shoppingcartapplication.model.ProductModel;
import com.demo.shoppingcartapplication.service.ShoppingCartService;

@Transactional
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	CartProductDao cartProductDao;

	@Autowired
	CartDao cartDao;

	@Autowired
	ApparalDao apparalDao;

	@Autowired
	UserDao userDao;

	@Autowired
	BookDao bookDao;

	@Autowired
	ProductDao productDao;

	List<Product> products = new ArrayList<>();
	private static CartProduct cartProduct = new CartProduct();

	static Logger logger = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

	@Override
	public String addProductInCart(int productId, int userId, int quantity) throws ServiceException {

		try {

			if (!userDao.existsByUserId(userId)) {
				logger.error("invalid userId:" + userId);
				throw new UserDoesNotExists("User does not exist!");
			}

			if (!productDao.existsById(productId)) {
				logger.error("productId:" + productId + " does not exist!");
				throw new ProductDoesNotExist("This item does not exist!");
			}

			else {
				User user = userDao.findByUserId(userId);
				Product product = productDao.findByProductId(productId);

				Cart cart = user.getCart();
				CartProductPk cartProductPk = new CartProductPk();
				cartProductPk.setCart(cart);
				cartProductPk.setProduct(product);

				// Reduce the quantity in product table
				List<Book> books = bookDao.findAllBooks();
				for (Book book : books) {
					if (book.getProductId() == productId) {
						if (book.getProductQuant() < quantity) {
							if (book.getProductQuant() == 0) {
								logger.error(book.getProductName() + " is out of stock!");
								throw new ProductQuantityExhausted("This item is out of stock");
							} else {
								logger.error("userId: " + userId + " tried to add more than " + book.getProductQuant()
										+ " items");
								throw new ProductQuantityExhausted(
										"You can not add more than " + book.getProductQuant() + " items");
							}

						}
						book.setProductQuant(book.getProductQuant() - quantity);
						bookDao.saveAndFlushBook(book);
					}
				}
				List<Apparal> apparals = apparalDao.findAllApparals();
				for (Apparal apparal : apparals) {
					if (apparal.getProductId() == productId) {

						if (apparal.getProductQuant() < quantity) {
							if (apparal.getProductQuant() == 0) {
								throw new ProductQuantityExhausted("This item is out of stock");
							} else
								throw new ProductQuantityExhausted(
										"You can not add more than " + apparal.getProductQuant() + " items");

						}

						apparal.setProductQuant(apparal.getProductQuant() - quantity);
						apparalDao.saveAndFlushApparal(apparal);
					}
				}

				cart.setTotalAmount(cart.getTotalAmount() + (product.getPrice() * quantity));
				cart.setTotalQuantity(cart.getTotalQuantity() + quantity);

				cartProduct.setCartProductPk(cartProductPk);
				cartProduct.setQuantity(quantity);
				cartProductDao.saveCartProduct(cartProduct);
				Cart savedCart = cartDao.saveCart(cart);
				if (savedCart != null) {
					logger.info("productId:" + productId + " has been added in cartId:" + cart.getCartId());
					return "Product has been added to cart successfully!";
				} else {
					logger.debug("productId:" + productId + " Could not be added in " + cart.getCartId()
							+ " Error occured whiling adding product in cart");
					return "Error occur";
				}

			}

		} catch (UserDoesNotExists | ProductDoesNotExist | ProductQuantityExhausted e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<ProductModel> getAllProducts(int userId) {
		List<Product> products = productDao.findAllProduct();

		List<ProductModel> productModels = new ArrayList<ProductModel>();
		User user = userDao.findByUserId(userId);
		for (Product product : products) {
			ProductModel productModel = new ProductModel();
			productModel.setProductAddedInCart(false);
			for (CartProduct cartProduct : user.getCart().getCartProducts()) {
				if (cartProduct.getCartProductPk().getProduct().getProductId() == product.getProductId()) {
					productModel.setProductAddedInCart(true);
				}
			}

			for (Book book : bookDao.findAllBooks()) {
				if (book.getProductId() == product.getProductId()) {
					productModel.setAuthorOrDesign(book.getAuthor());
					productModel.setGenreOrType(book.getGenre());
					productModel.setPublicationOrBrand(book.getPublication());
					productModel.setPictureURL(book.getBookPictureURL());
				}
			}

			for (Apparal apparal : apparalDao.findAllApparals()) {
				if (apparal.getProductId() == product.getProductId()) {
					productModel.setAuthorOrDesign(apparal.getDesign());
					productModel.setGenreOrType(apparal.getType());
					productModel.setPublicationOrBrand(apparal.getBrand());
					productModel.setPictureURL(apparal.getApparalPictureURL());
				}
			}

			productModel.setProductId(product.getProductId());
			productModel.setProductName(product.getProductName());
			productModel.setProductQuant(product.getProductQuant());
			productModel.setPrice(product.getPrice());
			productModels.add(productModel);
		}

		return productModels;
	}

	@Override
	public Product getProductById(int productId) throws ServiceException {
		try {
			if (!productDao.existsByProductId(productId)) {
				logger.error("productId: " + productId + " does not eist!");
				throw new ProductDoesNotExist("ProductDoesNotExist");
			}

		} catch (ProductDoesNotExist e) {
			throw new ServiceException(e.getMessage());
		}
		Product product = productDao.findByProductId(productId);
		return product;
	}

	@Override
	public Product getProductByName(String productName) throws ServiceException {
		try {

			Product product = new Product();
			List<Product> products = productDao.findAllProduct();
			for (Product product1 : products) {
				if (product1.getProductName().equals(productName)) {
					product = product1;

				}
			}
			if (product.getProductName() != (productName)) {
				logger.error("productId: " + product.getProductId() + " does not exist!");
				throw new ProductDoesNotExist("ProductDoesNotExist");
			}
			return product;
		} catch (ProductDoesNotExist e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<Product> getProductByCatagory(String catagory) throws ServiceException {
		try {
			List<Product> products = new ArrayList<Product>();
			List<Product> productList = productDao.findAllProduct();

			if (catagory.equalsIgnoreCase("book")) {

				for (Book book : bookDao.findAllBooks()) {
					for (Product product : productList) {
						if (book.getProductId() == product.getProductId()) {
							products.add(product);
						}
					}
				}

			}

			else if (catagory.equalsIgnoreCase("apparal")) {
				List<Apparal> apparals = apparalDao.findAllApparals();
				for (Apparal apparal : apparals) {
					for (Product product : productList) {
						if (apparal.getProductId() == product.getProductId()) {
							products.add(product);
						}
					}
				}

			} else {
				logger.error("Product does not exist!");
				throw new ProductDoesNotExist("This item does not exist!");

			}
			return products;
		} catch (ProductDoesNotExist e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Transactional
	@Override
	public String removeProductById(int productId, int userId) throws ServiceException {
		try {
			int flag1 = 0;
			CartProductPk cartProductPk = new CartProductPk();
			User user = userDao.findByUserId(userId);
			Cart cart = user.getCart();
			Product product = productDao.findByProductId(productId);

			cartProductPk.setCart(cart);
			;
			cartProductPk.setProduct(product);

			for (CartProduct cartProduct : cart.getCartProducts()) {
				if (cartProduct.getCartProductPk().getCart().getCartId() == cart.getCartId()
						&& cartProduct.getCartProductPk().getProduct().getProductId() == product.getProductId()) {

					flag1++;
				}
			}

			if (flag1 == 0) {
				logger.error("productId: " + productId + " is invalid!");
				throw new ProductDoesNotExist("Invalid product id ");
			} else {

				cartProduct = cartProductDao.findByCartProductId(cartProductPk);
				List<Apparal> apparals = apparalDao.findAllApparals();
				List<Book> books = bookDao.findAllBooks();
				for (Book book : books) {
					if (book.getProductId() == productId) {
						book.setProductQuant(book.getProductQuant() + cartProduct.getQuantity());
						bookDao.saveAndFlushBook(book);
					}
				}

				for (Apparal apparal : apparals) {
					if (apparal.getProductId() == productId) {
						apparal.setProductQuant(apparal.getProductQuant() + cartProduct.getQuantity());
						apparalDao.saveAndFlushApparal(apparal);
					}
				}

				cart.setTotalQuantity(cart.getTotalQuantity() - cartProduct.getQuantity());
				float amount = cartProduct.getCartProductPk().getProduct().getPrice() * cartProduct.getQuantity();
				cart.setTotalAmount(cart.getTotalAmount() - amount);

				cartProductDao.deleteCartProduct(cartProduct);
				cartDao.saveAndFlushCart(cart);

				if (!cartProductDao.existsByCartProductId(cartProductPk)) {
					return "Product has been removed from cart!";
				} else {
					return "Invalid";
				}
			}

		} catch (ProductDoesNotExist e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public String removeAllProduct(int userId) throws ServiceException {

		User user = userDao.findByUserId(userId);
		Cart cart = user.getCart();
		try {
			if (cart.getCartProducts().size() == 0) {
				logger.error("Cart is empty! Remove operation can not be performed.");
				throw new NoProductAvailableInCart("Noting is in cart!");
			}
		} catch (NoProductAvailableInCart e) {
			throw new ServiceException(e.getMessage());
		}

		List<CartProduct> cartProducts = cart.getCartProducts();
		for (CartProduct cartProduct : cartProducts) {
			Product product = productDao.findByProductId(cartProduct.getCartProductPk().getProduct().getProductId());
			product.setProductQuant(product.getProductQuant() + cartProduct.getQuantity());
			productDao.saveAndFlushProduct(product);
			cartProductDao.deleteCartProduct(cartProduct);
		}
		List<User> users = userDao.findAllUser();

		cart.setCartProducts(null);
		cart.setTotalAmount(0);
		cart.setTotalQuantity(0);
		Cart cart2 = cartDao.saveAndFlushCart(cart);
		for (User user1 : users) {
			if (user1.getCart().getCartId() == cart.getCartId()) {
				user1.setCart(cart2);
				userDao.saveAndFlushUser(user1);
			}
		}

		if (cart2.getTotalQuantity() == 0) {
			return "All products has removed from cart";
		} else {
			return "Product is not removed properly!";
		}

	}

	@Override
	public Cart updateQuantity(int userId, int productId, int quantity) throws ServiceException {
		try {
			User user = userDao.findByUserId(userId);
			Cart cart = cartDao.findByCartId(user.getCart().getCartId());

			if (!userDao.existsByUserId(userId)) {
				logger.error("userId: " + userId + " does not exist!");
				throw new UserDoesNotExist("User does not exist!");
			}

			for (CartProduct cartProduct : cart.getCartProducts()) {

				if (cartProduct.getCartProductPk().getProduct().getProductId() == productId) {

					Product product = productDao.findByProductId(productId);
					if (quantity > 0) {
						cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
						product.setProductQuant(product.getProductQuant() - quantity);
						cart.setTotalQuantity(cart.getTotalQuantity() + quantity);
						cart.setTotalAmount(cart.getTotalAmount() + (product.getPrice() * quantity));
					}

					if (quantity < 0) {
						int checkQuantity = cartProduct.getQuantity() - quantity;

						if (checkQuantity == 0) {
							product.setProductQuant(product.getProductQuant() + quantity);
							cart.setTotalQuantity(cart.getTotalQuantity() - quantity);
							cart.setTotalAmount(cart.getTotalAmount() - (product.getPrice() * quantity));
							cartProductDao.deleteCartProduct(cartProduct);
						} else {
							cartProduct.setQuantity(cartProduct.getQuantity() - quantity);
							product.setProductQuant(product.getProductQuant() - quantity);
							cart.setTotalQuantity(cart.getTotalQuantity() - quantity);
							cart.setTotalAmount(cart.getTotalAmount() - (product.getPrice() * quantity));

						}
					}
					cartProductDao.saveAndFlashCartProduct(cartProduct);
					productDao.saveAndFlushProduct(product);
					cartDao.saveAndFlushCart(cart);
				}
			}

			return cart;

		} catch (UserDoesNotExist e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<ProductModel> getProductByAny(String variable) throws ServiceException {
		try {
			List<ProductModel> productModels = new ArrayList<ProductModel>();

			List<Product> products = productDao.findAllProduct();
			Product product = new Product();

			ProductModel productModel = new ProductModel();
			int checkProduct = 0;
			String catagory = variable;
			int flag = 0;

			for (Product product1 : products) {
				if (product1.getProductName().equalsIgnoreCase(variable)) {
					flag++;

					product = getProductByName(product1.getProductName());

					productModel.setProductId(product.getProductId());
					productModel.setProductName(product.getProductName());
					productModel.setProductQuant(product.getProductQuant());
					productModel.setPrice(product.getPrice());
					getProductDetails(product, productModel);
					productModel.setProductAddedInCart(false);
					for (Cart cart : cartDao.findAllCart()) {
						for (CartProduct cartProduct : cart.getCartProducts()) {
							if (cartProduct.getCartProductPk().getProduct().getProductId() == product.getProductId()) {
								productModel.setProductAddedInCart(true);
							}
						}

					}
					productModels.add(productModel);
					checkProduct++;
					return productModels;
				}
			}

			for (Product product1 : products) {
				if (catagory.equalsIgnoreCase("book") || catagory.equalsIgnoreCase("apparal")) {
					break;
				}
				if (product1.getProductId() == Integer.parseInt(variable)) {
					product = getProductById(product1.getProductId());

					productModel.setProductId(product.getProductId());
					productModel.setProductName(product.getProductName());
					productModel.setProductQuant(product.getProductQuant());
					productModel.setPrice(product.getPrice());
					getProductDetails(product, productModel);
					productModel.setProductAddedInCart(false);
					for (Cart cart : cartDao.findAllCart()) {
						for (CartProduct cartProduct : cart.getCartProducts()) {
							if (cartProduct.getCartProductPk().getProduct().getProductId() == product.getProductId()) {
								productModel.setProductAddedInCart(true);
							}
						}

					}
					productModels.add(productModel);
					checkProduct++;
					return productModels;

				}
			}

			if (catagory.equalsIgnoreCase("book") || catagory.equalsIgnoreCase("apparal")) {

				products = getProductByCatagory(catagory);

				for (Product product2 : products) {
					ProductModel productModel1 = new ProductModel();

					productModel1.setProductId(product2.getProductId());
					productModel1.setProductName(product2.getProductName());
					productModel1.setProductQuant(product2.getProductQuant());
					productModel1.setPrice(product2.getPrice());
					productModel1.setProductAddedInCart(false);
					getProductDetails(product2, productModel1);
					for (Cart cart : cartDao.findAllCart()) {
						for (CartProduct cartProduct : cart.getCartProducts()) {
							if (cartProduct.getCartProductPk().getProduct().getProductId() == product2.getProductId()) {
								productModel1.setProductAddedInCart(true);
							}
						}
					}
					productModels.add(productModel1);
				}
				checkProduct++;
				return productModels;
			} else {
				logger.error("Invalid search query!");
				throw new ProductDoesNotExist("Invalid input!");
			}

		} catch (ProductDoesNotExist e) {
			throw new ServiceException(e.getMessage());
		}
	}

	private void getProductDetails(Product product, ProductModel productModel) {
		for (Book book : bookDao.findAllBooks()) {
			if (product.getProductId() == book.getProductId()) {
				productModel.setAuthorOrDesign(book.getAuthor());
				productModel.setGenreOrType(book.getGenre());
				productModel.setPublicationOrBrand(book.getPublication());
				productModel.setPictureURL(book.getBookPictureURL());
			}
		}

		for (Apparal apparal : apparalDao.findAllApparals()) {
			if (apparal.getProductId() == product.getProductId()) {
				productModel.setAuthorOrDesign(apparal.getDesign());
				productModel.setGenreOrType(apparal.getType());
				productModel.setPublicationOrBrand(apparal.getBrand());
				productModel.setPictureURL(apparal.getApparalPictureURL());
			}
		}

	}

	@Override
	public CartDto viewCartById(int userId) {
		User user = userDao.findByUserId(userId);
		Cart cart = user.getCart();
		CartDto cartDto = new CartDto();
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		cartDto.setTotalAmount(cart.getTotalAmount());
		cartDto.setTotalQuantity(cart.getTotalQuantity());

		for (CartProduct cartProduct : cart.getCartProducts()) {
			ProductDto productDto = new ProductDto();
			productDto.setProductId(cartProduct.getCartProductPk().getProduct().getProductId());
			productDto.setProductName(cartProduct.getCartProductPk().getProduct().getProductName());
			productDto.setProductPrice(cartProduct.getCartProductPk().getProduct().getPrice());
			productDto.setQuantity(cartProduct.getQuantity());
			productDtos.add(productDto);
		}
		cartDto.setProductDtos(productDtos);
		return cartDto;
	}

	@Override
	public String increaseQuantity(int userId, int productId) throws ServiceException {

		User user = userDao.findByUserId(userId);
		Cart cart = cartDao.findByCartId(user.getCart().getCartId());
		int checkQuantity = 0;
		Cart savedCart = new Cart();

		List<CartProduct> cartProducts = cart.getCartProducts();
		try {
			for (CartProduct cartProduct2 : cartProducts) {
				CartProduct cartProduct = new CartProduct();
				cartProduct = cartProduct2;
				Product product = productDao.findByProductId(productId);

				if (cartProduct.getCartProductPk().getCart().getCartId() == cart.getCartId()
						&& cartProduct.getCartProductPk().getProduct().getProductId() == product.getProductId()) {

					if (product.getProductQuant() == 0) {
						logger.error("Product is out of stock!");
						throw new ProductQuantityExhausted("This item is out of stock!");

					} else {
						product.setProductQuant(product.getProductQuant() - 1);
						productDao.saveAndFlushProduct(product);

						cartProduct.setQuantity(cartProduct.getQuantity() + 1);
						cartProductDao.saveAndFlashCartProduct(cartProduct);

						checkQuantity = cart.getTotalQuantity();
						cart.setTotalAmount(cart.getTotalAmount() + product.getPrice());
						cart.setTotalQuantity(cart.getTotalQuantity() + 1);
						savedCart = cartDao.saveAndFlushCart(cart);
						if (savedCart.getTotalQuantity() == checkQuantity + 1) {
							return "Product has been updated!";
						} else {

							return "Not updated";
						}
					}

				}
			}

			if (savedCart.getTotalQuantity() == checkQuantity + 1) {

				return "Product has been updated!";
			} else {

				return "Not updated";
			}

		} catch (ProductQuantityExhausted e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public String decreaseQuantity(int userId, int productId) throws ServiceException {

		User user = userDao.findByUserId(userId);
		Cart cart = cartDao.findByCartId(user.getCart().getCartId());

		int checkQuantity = 0;
		Cart savedCart = new Cart();

		for (CartProduct cartProduct : cart.getCartProducts()) {
			if (cartProduct.getCartProductPk().getCart().getCartId() == cart.getCartId()
					&& cartProduct.getCartProductPk().getProduct().getProductId() == productId) {

				Product product = productDao.findByProductId(productId);

				product.setProductQuant(product.getProductQuant() + 1);
				productDao.saveAndFlushProduct(product);

				cartProduct.setQuantity(cartProduct.getQuantity() - 1);
				cartProductDao.saveAndFlashCartProduct(cartProduct);

				if (cartProduct.getQuantity() == 0) {
					cartProductDao.deleteCartProduct(cartProduct);
				}
				checkQuantity = cart.getTotalQuantity();

				cart.setTotalAmount(cart.getTotalAmount() - product.getPrice());
				cart.setTotalQuantity(cart.getTotalQuantity() - 1);
				savedCart = cartDao.saveAndFlushCart(cart);

				if (savedCart.getTotalQuantity() == (checkQuantity - 1) || cartProduct.getQuantity() == 0) {

					return "Product has been updated!";
				} else {
					return "Not updated";
				}

			}
		}
		if (savedCart.getTotalQuantity() == (checkQuantity - 1) || cartProduct.getQuantity() == 0) {
			return "Product has been updated!";
		} else {
			return "Not updated";
		}

	}

}
