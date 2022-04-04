package com.demo.shoppingcartapplication.dao;

import java.util.List;

import com.demo.shoppingcartapplication.entity.User;

public interface UserDao {

	boolean existsByUserId(int userId);

	User saveUser(User user);

	List<User> findAllUser();

	User findByUserId(int userId);

	User saveAndFlushUser(User user);

}
