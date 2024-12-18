package com.springmvc.controller;

import java.text.DateFormat;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)  // doGet과 같은 동작
	public String home(Locale locale, Model model) {
		// /WEB-INF/views/		.jsp  뷰리졸브
      Date date = new Date();
      DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
      
      String formattedDate = dateFormat.format(date);
      
      model.addAttribute("serverTime", formattedDate );
		
		return "home"; // 뷰 ↑
	}
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test(Model model) {
		String test = "안녕하세요!";
		model.addAttribute("tt",test);
		return "test";
	}
	
}
