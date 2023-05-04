package com.ensias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.ensias.model.Member;
import com.ensias.repository.MemberRepository;

@SpringBootApplication
public class MemberServiceApplication implements CommandLineRunner {
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(MemberServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 
		repositoryRestConfiguration.exposeIdsFor(Member.class) ;
		memberRepository.save(new Member(null, "Alami", "Walid", "2025", "GL"));
		memberRepository.save(new Member(null, "Elmrabti", "Hamza", "2024", "GL"));
		memberRepository.save(new Member(null, "Tourabi", "Asmaa", "2025", "GL"));
		memberRepository.save(new Member(null, "Chawki", "Imane", "2025", "GL"));
		
	}

}
