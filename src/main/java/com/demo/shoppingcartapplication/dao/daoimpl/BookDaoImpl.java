package com.demo.shoppingcartapplication.dao.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.shoppingcartapplication.dao.BookDao;
import com.demo.shoppingcartapplication.entity.Book;
import com.demo.shoppingcartapplication.repository.BookRepository;

@Component
public class BookDaoImpl implements BookDao {

	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book saveAndFlushBook(Book book) {
		return bookRepository.saveAndFlush(book);
	}

}
