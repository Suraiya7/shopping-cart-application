package com.demo.shoppingcartapplication.dao;

import java.util.List;

import com.demo.shoppingcartapplication.entity.Apparal;

public interface ApparalDao {

	List<Apparal> findAllApparals();

	Apparal saveAndFlushApparal(Apparal apparal);

}
