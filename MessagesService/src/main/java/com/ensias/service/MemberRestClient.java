package com.ensias.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ensias.model_aux.Member;

@FeignClient(name = "MEMBER-SERVICE")
public interface MemberRestClient {
	@GetMapping(path = "/members/{id}")
	public Member findMemberById(@PathVariable int id) ;

}
