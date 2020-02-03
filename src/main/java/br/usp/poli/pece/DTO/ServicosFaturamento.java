package br.usp.poli.pece.DTO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServicosFaturamento{
	@JsonProperty("qtChamadasService")
	private BigDecimal qtChamadasService;
	@JsonProperty("valor")
	private BigDecimal valor;
	@JsonProperty("servico_name")
	private String servicoName;
	@JsonProperty("uri")
	private String uri;
	@JsonProperty("servico_uri")
	private String servicoUri;
	@JsonProperty("remoteAddress")
	private String remoteAddress;
	
	
	@JsonProperty("total_servico")
	public BigDecimal getValorTotal() {
		return valor.multiply(qtChamadasService);
	}
	
}

