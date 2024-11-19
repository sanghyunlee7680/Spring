package com.springmvc.chap09;


import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam03")
public class Example03Controller {
	
	@GetMapping("/form")
	public String requestForm() 
	{
		System.out.println("form(GET)요청 requestForm()실행");
		return "webpage09_02";
	}
	
	@PostMapping("/form")
	public String submitForm(@ModelAttribute("member") Member member, HttpServletRequest request, HttpSession session) {
		System.out.println("form(POST)요청 submitForm()실행");
		String name = member.getName();
		System.out.println("name : " + name);
		// 1.파일이름 만들기
		String filename = member.getImageFile().getOriginalFilename();
		// 2.빈 파일 생성
		System.out.println("filename:"+filename);
		File f = new File("c:\\upload\\"+name+"_"+filename);
		try {
		// 3. 파일에 내용 작성	
			member.getImageFile().transferTo(f);
		} catch (Exception e) {e.printStackTrace();}
		
		return "webpage09_submit";
		
	}
}
