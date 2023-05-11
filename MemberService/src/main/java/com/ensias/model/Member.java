package com.ensias.model;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model_aux.Message;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	private String firstName ;
	private String lastName ;
	private String promo ;   // meme chose
	private String filiere ; //il faut creer un classse
	//@OneToMany(mappedBy = "member")
	@OneToMany(mappedBy = "member")
	@Transient
	private Collection<Message> messagesEnvoyes ;
	

}
