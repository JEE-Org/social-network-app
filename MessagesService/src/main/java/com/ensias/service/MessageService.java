package com.ensias.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensias.model.Message;
import com.ensias.repository.MessageRepository;

@Service
class MessageService implements IMessageService {
	
	@Autowired
	private MessageRepository messageRepository ;
	
	public Message sendMessage(String body, int sender, int receiver){
		Message message = new Message(null, body, sender, receiver) ;
		return messageRepository.save(message) ;
	}
	
	public void deleteMessage(int messageId) {
		
	}
	

}
