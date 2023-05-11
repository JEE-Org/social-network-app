package com.ensias.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import model_aux.Message;

@FeignClient(name = "MESSAGE-SERVICE")
public interface MessageRestClient {
	
	@GetMapping(path = "/messages/{id}")
	public Message findMessageById(@PathVariable int id) ;
	

}
