package br.usp.poli.pece.DTO;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.crypto.Data;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties
public class CobrancaServicoTopicDTO {

	@Id
	private String id;
	
	
	private Date dataRequisicao;
	
	@JsonProperty("proxyResponseTimeMs")
	private Integer proxyResponseTimeMs;
	@JsonProperty("proxyLatencyMs")
	private Integer proxyLatencyMs;
	@JsonProperty("apiResponseTimeMs")
	private Integer apiResponseTimeMs;
	@JsonProperty("requestId")
	private String requestId;
	@JsonProperty("api")
	private String api;
	@JsonProperty("application")
	private String application;
	@JsonProperty("transactionId")
	private String transactionId;
	@JsonProperty("tenant")
	private Object tenant;
	@JsonProperty("message")
	private Object message;
	@JsonProperty("plan")
	private String plan;
	@JsonProperty("localAddress")
	private String localAddress;
	@JsonProperty("remoteAddress")
	private String remoteAddress;
	@JsonProperty("httpMethod")
	private String httpMethod;
	@JsonProperty("host")
	private String host;
	@JsonProperty("uri")
	private String uri;
	@JsonProperty("requestContentLength")
	private Integer requestContentLength;
	@JsonProperty("responseContentLength")
	private Integer responseContentLength;
	@JsonProperty("status")
	private Integer status;
	@JsonProperty("endpoint")
	private String endpoint;
	@JsonProperty("log")
	private Object log;
	@JsonProperty("path")
	private String path;
	@JsonProperty("mappedPath")
	private Object mappedPath;
	@JsonProperty("userAgent")
	private String userAgent;
	@JsonProperty("user")
	private Object user;
	@JsonProperty("securityType")
	private Object securityType;
	@JsonProperty("securityToken")
	private Object securityToken;
	@JsonProperty("errorKey")
	private Object errorKey;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public CobrancaServicoTopicDTO() {
		this.dataRequisicao = Calendar.getInstance().getTime();
	}
	
	
}
