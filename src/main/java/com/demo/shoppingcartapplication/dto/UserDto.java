package com.demo.shoppingcartapplication.dto;

import com.demo.shoppingcartapplication.entity.Cart;

public class UserDto {

	private int userId;

	private String userName;

	private Cart cart;

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(int userId, String userName, Cart cart) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.cart = cart;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userName=" + userName + ", cart=" + cart + "]";
	}

}
