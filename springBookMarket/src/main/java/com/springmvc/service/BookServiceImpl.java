package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.domain.Book;
import com.springmvc.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository; //다형성

	public List<Book> getAllBookList() {
		System.out.println("BookService이 getAllBookList() 실행");
		return bookRepository.getAllBookList();
	}
}
