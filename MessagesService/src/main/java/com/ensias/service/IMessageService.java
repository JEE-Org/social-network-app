package com.ensias.service;

import org.springframework.stereotype.Service;

import com.ensias.model.Message;

public interface IMessageService {

	public Message sendMessage(String body, int sender, int receiver);
	public void deleteMessage(int messageId);
}
