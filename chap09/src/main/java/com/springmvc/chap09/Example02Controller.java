package com.springmvc.chap09;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/exam02")
public class Example02Controller {

	@GetMapping("/form")
	public String requestForm() {
		System.out.println("form(GET)요청 requestForm()실행");
		return "webpage09_01";
	}
	
	@PostMapping("/form")
	public String submitForm(MultipartHttpServletRequest request) {
		System.out.println("form(POST)요청 submitForm()실행");
		String name = request.getParameter("name");
		MultipartFile file = request.getFile("fileImage");
		String images = request.getServletContext().getRealPath("resources/images");
		System.out.println("images:" + images);
		String filename = file.getOriginalFilename();
		File f = new File("c:\\upload\\" + name + "_" + filename);
		try 
		{
			file.transferTo(f);
		} 
		catch (Exception e) {e.printStackTrace();}
		
		return "webpage09_submit";
	}
	
}
