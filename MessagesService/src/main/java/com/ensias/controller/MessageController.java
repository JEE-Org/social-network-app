package com.ensias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensias.model.Message;
import com.ensias.service.IMessageService;

@RestController
public class MessageController {
	
	@Autowired
	private IMessageService messageService ;
	
	/*
	@PostMapping(path = "/sendMessage")
	public Message sendMessage() {
		messageService.sendMessage(null, 0, 0) ;
		// TODO 
		// prendre les paramètres
		return null ;
	}
*/
	
}
