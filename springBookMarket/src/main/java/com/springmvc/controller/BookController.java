package com.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.domain.Book;
//import com.springmvc.exception.BookIdException;
import com.springmvc.exception.CategoryException;
import com.springmvc.service.BookService;
import com.springmvc.validator.BookValidator;
import com.springmvc.validator.UnitsInStockValidator;


@Controller
@RequestMapping("/books")
public class BookController {
	// BookService 클래스도 component-scan 되어야 한다. 
	@Autowired
	private BookService bookService;
	
	// BookValidator 인스턴스 선언
	@Autowired
	private BookValidator bookValidator; 
	
	//UnitsInStockValidator의 인스턴스 선언
//	@Autowired
//	private UnitsInStockValidator unitsInStockValidator;
	
	@GetMapping
	public String requestBookList(Model model) {
		System.out.println("default요청 BookController의 requestBookList() 실행");
		List<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList", list);
		return "books";
	}
	
	@GetMapping("/all")
	public ModelAndView requestAllBooks(Model model) {
		System.out.println("/all요청 BookController의 requestAllBooks()실행");
		ModelAndView modelAndView = new ModelAndView();
		List<Book> list = bookService.getAllBookList();
		modelAndView.addObject("bookList", list);
		modelAndView.setViewName("books");
		return modelAndView;
	}
	
	@GetMapping("/{category}")
	public String requestBooksByCategory(@PathVariable("category") String bookCategory,Model model) {
		System.out.println("/{category}요청 BookController의 requestBooksByCategory() 실행");
		List<Book> booksByCategory = bookService.getBookListByCategory(bookCategory);
		if(booksByCategory == null || booksByCategory.isEmpty()) {
			throw new CategoryException();
		}
		model.addAttribute("bookList", booksByCategory);
		return "books";
	}
	
	@GetMapping("/filter/{bookFilter}")
	public String requestBooksByFilter(@MatrixVariable(pathVar="bookFilter") Map<String, List<String>> bookFilter, Model model)
	{
		System.out.println("/filter/{bookFilter}요청 BookController의 requestBooksByFilter()실행");
		Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);
		model.addAttribute("bookList", booksByFilter);
		return "books";
	}
	
	@GetMapping("/book")
	public String requestBookById(@RequestParam("id") String bookId, Model model) {
		System.out.println("/book요청 BookController의 requestBookById()실행");
		Book bookById = bookService.getBookById(bookId);
		model.addAttribute("book", bookById);
		return "book";
	}
	
	@GetMapping("/add")
	public String requestAddBookForm(@ModelAttribute("NewBook") Book book) {
		return "addBook";
	}
	
	@PostMapping("/add")
	public String submitAddNewBook(@Valid @ModelAttribute("NewBook") Book book, BindingResult result,MultipartHttpServletRequest request) {
		
		if(result.hasErrors()) {
			return "addBook";
		}
		// String[] suppressedFields = result.getSuppressedFields();
		
		MultipartFile bookImage = book.getBookImage();
		
		String saveName = bookImage.getOriginalFilename();
		String images = request.getServletContext().getRealPath("resources/images");
		File f = new File(images, saveName);
		
		if(bookImage != null && !bookImage.isEmpty()) {
			try {
				bookImage.transferTo(f);
			} catch (Exception e) {
				throw new RuntimeException("도서 이미지 업로드가 실패했습니다", e);
			}
		}
		bookService.setNewBook(book);
		return "redirect:/books";
	}
	
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("addTitle", "신규 도서 등록");
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 생성한 bookValidator설정
		binder.setValidator(bookValidator);
		// 생성한 unitsInStockValidator설정
		// binder.setValidator(unitsInStockValidator);
		binder.setAllowedFields("bookId", "name", "unitPrice", "author", "description", "publisher", "category", "unitsInStock", "totalPages", "releaseDate", "condition", "bookImage");
	}
	
//	@ExceptionHandler(value={BookIdException.class})
//	public ModelAndView handlerError(HttpServletRequest req, BookIdException exception) {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("invalidBookId", exception.getBookId());
//		mav.addObject("exception", exception);
//		mav.addObject("url", req.getRequestURL()+ "?" + req.getQueryString());
//		mav.setViewName("errorBook");
//		return mav;
//	}
	
}
