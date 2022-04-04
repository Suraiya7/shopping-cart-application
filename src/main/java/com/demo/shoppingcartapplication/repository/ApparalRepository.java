package com.demo.shoppingcartapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.shoppingcartapplication.entity.Apparal;
@Repository
public interface ApparalRepository extends JpaRepository<Apparal, Integer> {

}
