package com.demo.shoppingcartapplication.dto;

import java.util.List;

public class CartDto {

	private int totalQuantity;

	private float totalAmount;

	List<ProductDto> productDtos;

	public CartDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartDto(int totalQuantity, float totalAmount, List<ProductDto> productDtos) {
		super();
		this.totalQuantity = totalQuantity;
		this.totalAmount = totalAmount;
		this.productDtos = productDtos;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<ProductDto> getProductDtos() {
		return productDtos;
	}

	public void setProductDtos(List<ProductDto> productDtos) {
		this.productDtos = productDtos;
	}

	@Override
	public String toString() {
		return "CartDto [totalQuantity=" + totalQuantity + ", totalAmount=" + totalAmount + ", productDtos="
				+ productDtos + "]";
	}

}
