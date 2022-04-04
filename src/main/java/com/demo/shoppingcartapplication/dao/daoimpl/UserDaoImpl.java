package com.demo.shoppingcartapplication.dao.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.shoppingcartapplication.dao.UserDao;
import com.demo.shoppingcartapplication.entity.User;
import com.demo.shoppingcartapplication.repository.UserRepository;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	UserRepository userRepository;

	@Override
	public boolean existsByUserId(int userId) {

		return userRepository.existsById(userId);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findAllUser() {

		return userRepository.findAll();
	}

	@Override
	public User findByUserId(int userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId).get();
	}

	@Override
	public User saveAndFlushUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.saveAndFlush(user);
	}

}
