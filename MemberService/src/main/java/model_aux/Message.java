package model_aux;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class Message {
	
	private Integer id;
	private String content ;
	@ManyToOne
	private int idSender ;
	private int idReceiver ;

}
