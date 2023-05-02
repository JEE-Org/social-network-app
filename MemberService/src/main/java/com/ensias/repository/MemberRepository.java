package com.ensias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ensias.model.Member;

@RepositoryRestResource
public interface MemberRepository extends JpaRepository<Member, Integer> {
	
}
