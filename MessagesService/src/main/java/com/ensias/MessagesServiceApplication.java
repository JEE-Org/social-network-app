package com.ensias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.ensias.model.Message;
import com.ensias.model_aux.Member;
import com.ensias.repository.MessageRepository;
import com.ensias.service.MemberRestClient;

@SpringBootApplication
@EnableFeignClients
public class MessagesServiceApplication implements CommandLineRunner{

	@Autowired
	private MessageRepository messageRepository ;
	@Autowired
	private MemberRestClient memberRestClient ;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(MessagesServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Member member = memberRestClient.findMemberById(2) ;
		System.out.println(member);
		
		messageRepository.save(new Message(null, "Message de test 1", 1, 2, member) ) ;	
		messageRepository.save(new Message(null, "Message de test 2", 1, 3, member) ) ;
		messageRepository.save(new Message(null, "Message de test 3", 1, 2, member) ) ;

	}

}
