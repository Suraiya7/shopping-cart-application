package com.demo.shoppingcartapplication.dto;

public class ProductDto {

	private int productId;

	private int quantity;

	private float productPrice;

	private String productName;

	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDto(int productId, int quantity, float productPrice, String productName) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.productPrice = productPrice;
		this.productName = productName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "ProductDto [productId=" + productId + ", quantity=" + quantity + ", productPrice=" + productPrice
				+ ", productName=" + productName + "]";
	}

}
