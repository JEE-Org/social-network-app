package com.ensias.model;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	private String nom ;
	private String prenom ;
	private String promo ;
	private String filiere ;
	@OneToMany(mappedBy = "member")
	private Collection<Message> messagesEnvoyes ;
	

}
