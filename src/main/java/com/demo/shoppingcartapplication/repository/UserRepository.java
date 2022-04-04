package com.demo.shoppingcartapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.shoppingcartapplication.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
