package com.kidwiz.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kidwiz.web.DTO.TestDTO;
import com.kidwiz.web.service.VueService;

@RestController
public class Test {
	
	@Autowired
	private VueService vueService;
	
	@PostMapping("/testPost")
	public String postTest(@RequestBody TestDTO test) {
		vueService.write(test);
		System.out.println(test.getName());
		return "실험중";
	}

}
