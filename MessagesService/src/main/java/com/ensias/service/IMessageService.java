package com.ensias.service;

import org.springframework.stereotype.Service;

import com.ensias.model.Message;
import com.ensias.model_aux.Member;

public interface IMessageService {

	public Message sendMessage(String body, int sender, int receiver, Member member);
	public void deleteMessage(int messageId);
}
