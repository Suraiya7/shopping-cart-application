package com.demo.shoppingcartapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.shoppingcartapplication.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
