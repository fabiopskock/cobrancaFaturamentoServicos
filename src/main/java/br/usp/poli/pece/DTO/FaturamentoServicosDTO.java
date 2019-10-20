package br.usp.poli.pece.DTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FaturamentoServicosDTO {
	
	@JsonProperty("_id")
	private FaturamentoServicosIDDTO id;
	@JsonProperty("count")
	private Integer count;
	@JsonProperty("competencia")
	private String competencia;
	@JsonProperty("valor")
	private Double valor;
	@JsonProperty("servico_uri")
	private String servicoUri;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	

	
}

@Getter
@Setter
class FaturamentoServicosIDDTO{
	
	@JsonProperty("uri")
	private String uri;
	@JsonProperty("competencia")
	private String competencia;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	
	
}