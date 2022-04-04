package com.demo.shoppingcartapplication.dao.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.shoppingcartapplication.dao.ApparalDao;
import com.demo.shoppingcartapplication.entity.Apparal;
import com.demo.shoppingcartapplication.repository.ApparalRepository;

@Component
public class ApparalDaoImpl implements ApparalDao {

	@Autowired
	ApparalRepository apparalRepository;

	@Override
	public List<Apparal> findAllApparals() {
		return apparalRepository.findAll();
	}

	@Override
	public Apparal saveAndFlushApparal(Apparal apparal) {
		// TODO Auto-generated method stub
		return apparalRepository.saveAndFlush(apparal);
	}

}
