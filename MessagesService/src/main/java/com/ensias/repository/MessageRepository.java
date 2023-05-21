package com.ensias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.ensias.model.Message;

@RepositoryRestResource
public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	

}
