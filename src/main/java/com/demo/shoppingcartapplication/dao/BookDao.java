package com.demo.shoppingcartapplication.dao;

import java.util.List;

import com.demo.shoppingcartapplication.entity.Book;

public interface BookDao {

	List<Book> findAllBooks();

	Book saveAndFlushBook(Book book);

}
