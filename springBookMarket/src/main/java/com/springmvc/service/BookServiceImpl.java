package com.springmvc.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public List<Book> getBookListByCategory(String category) {
		System.out.println("BookService에 getBookListByCategory() 실행");
		List<Book> booksByCategory = bookRepository.getBookListByCategory(category);
		return booksByCategory;
	}
	
	public Set<Book> getBookListByFilter(Map<String, List<String>> filter){
		System.out.println("BookService에 getBookListByFilter() 실행");
		Set<Book> booksByFilter = bookRepository.getBookListByFilter(filter);
		return booksByFilter;
	}
	
	public Book getBookById(String bookId) {
		System.out.println("BookService에 getBookById() 실행");
		Book bookById = bookRepository.getBookById(bookId);
		return bookById;
	}
	
	public void setNewBook(Book book) {
		bookRepository.setNewBook(book);
	}
}
