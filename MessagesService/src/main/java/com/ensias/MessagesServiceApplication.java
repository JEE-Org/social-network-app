package com.ensias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ensias.model.Message;
import com.ensias.repository.MessageRepository;

@SpringBootApplication
public class MessagesServiceApplication implements CommandLineRunner{

	@Autowired
	private MessageRepository messageRepository ;
	
	public static void main(String[] args) {
		SpringApplication.run(MessagesServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		messageRepository.save(new Message(null, "Message de test 1", 1, 2) ) ;	
		messageRepository.save(new Message(null, "Message de test 2", 2, 3) ) ;
		messageRepository.save(new Message(null, "Message de test 3", 4, 2) ) ;
		messageRepository.save(new Message(null, "Message de test 4", 1, 3) ) ;
		messageRepository.save(new Message(null, "Message de test 5", 2, 4) ) ;

	}

}
