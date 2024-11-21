package com.springmvc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.springmvc.domain.Book;
import com.springmvc.service.BookService;

public class BookIdValidator implements ConstraintValidator<BookId, String>{
	
	@Autowired
	private BookService bookService;
	@Override
	public void initialize(BookId constraintAnnotation) {
		// @BookId 정보 초기화 메서드
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// 유효성 검사 메서드
		Book book;
		book = bookService.getBookById(value);
		if(book!=null) {
			return false;			
		}
		return true;
	}

}
