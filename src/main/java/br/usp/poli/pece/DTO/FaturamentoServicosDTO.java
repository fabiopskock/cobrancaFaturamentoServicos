package br.usp.poli.pece.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FaturamentoServicosDTO {
	
	private String mesReferencia;
	
	private List<ServicoCobrancaDto> servicos;
	
	private Double valorFinalFaturamento;
	
}

class ServicoCobrancaDto{
	
	private String nomeServico;
	private Integer quantidadeCobranca;
	private Float valorUnitario;
	private Double valorFinalServico;
	
	
}