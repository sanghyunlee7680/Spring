package com.springmvc.chap09;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/exam01")
public class Example01Controller {

	@GetMapping("/form")
	public String requestForm() {
		System.out.println("form(GET)요청 requestForm()실행");
		return "webpage09_01";
	}
	
	@PostMapping("/form")
	public String submitForm(@RequestParam("name") String name,
							@RequestParam("fileImage") MultipartFile file, HttpServletRequest request) {
		System.out.println("form(POST)요청 submitForm()실행");
		String images = request.getServletContext().getRealPath("resources/images");
		System.out.println("images:" + images);
		String filename = file.getOriginalFilename();
		File f = new File(images + "\\" + name + "_" + filename);
		try 
		{
			file.transferTo(f);
		} 
		catch (Exception e) {e.printStackTrace();}
		
		return "webpage09_submit";
	}
	
}
