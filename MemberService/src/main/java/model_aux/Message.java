package model_aux;

import lombok.Data;

@Data
public class Message {
	
	private Integer id;
	private String content ;
	private int idSender ;
	private int idReceiver ;

}
