package br.usp.poli.pece.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MensagemDTO {
	
	
	private String msg;
	
	public MensagemDTO(String msg) {
		this.msg = msg;
	}
	
}
