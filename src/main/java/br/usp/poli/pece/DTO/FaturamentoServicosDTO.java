package br.usp.poli.pece.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FaturamentoServicosDTO {

	@JsonProperty("servicosFaturados")
	private List<ServicosFaturamento> servicos;
	
	
	@JsonProperty("total_fatura")
	public Double calcularValorTotal() {
		Double totalFatura = 0.0;
		for (ServicosFaturamento servicosFaturamento : servicos) 
			totalFatura +=  servicosFaturamento.getValorTotal();
		
		return totalFatura;
	}
	
	public FaturamentoServicosDTO(List<ServicosFaturamento> servicos) {
		this.servicos = servicos;
	}
	
}

