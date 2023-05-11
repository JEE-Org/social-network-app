package com.ensias.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberContoller {
	
	@ResponseBody
	@GetMapping(path = "/")
	public String home() {
		return "redirect:members";
		
	}
	

}
