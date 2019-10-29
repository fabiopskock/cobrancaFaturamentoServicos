package br.usp.poli.pece.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServicosFaturamento{
	@JsonProperty("qtChamadasService")
	private Integer qtChamadasService;
	@JsonProperty("valor")
	private Double valor;
	@JsonProperty("servico_name")
	private String servicoName;
	@JsonProperty("uri")
	private String uri;
	@JsonProperty("servico_uri")
	private String servicoUri;
	@JsonProperty("remoteAddress")
	private String remoteAddress;
//	@JsonIgnore
//	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	
	@JsonProperty("total_servico")
	public Double getValorTotal() {
		return valor*qtChamadasService;
	}
	
}

