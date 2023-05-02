package com.ensias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ensias.model.Member;
import com.ensias.repository.MemberRepository;

@SpringBootApplication
public class MemberServiceApplication implements CommandLineRunner {
	
	@Autowired
	private MemberRepository memberRepository;

	public static void main(String[] args) {
		SpringApplication.run(MemberServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		memberRepository.save(new Member(null, "Alami", "Walid", "2025", "GL"));
		memberRepository.save(new Member(null, "Elmrabti", "Hamza", "2024", "GL"));
		memberRepository.save(new Member(null, "Tourabi", "Asmaa", "2025", "GL"));
		memberRepository.save(new Member(null, "Chawki", "Imane", "2025", "GL"));
		
	}

}
