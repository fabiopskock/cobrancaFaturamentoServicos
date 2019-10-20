package br.usp.poli.pece.listener;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.usp.poli.pece.DTO.CobrancaServicoTopicDTO;
import br.usp.poli.pece.service.FaturamentoService;

@RestController
public class ListenerCobrancaServicos {
	
	@Autowired
	private FaturamentoService faturamentoService;
	
	@KafkaListener(groupId = "groupID-Listener", 
					topics = "test",
					containerFactory = "kafkaListenerContainerFactory")
	public void getMsgFromTopicAPI(String cobrancaServicoDtoStr) {
				
		try {
			if(!StringUtils.containsIgnoreCase(cobrancaServicoDtoStr, "jvm")) {
				ObjectMapper mapper = new ObjectMapper();
				CobrancaServicoTopicDTO cobrancaServicoDto =
					mapper.readValue(cobrancaServicoDtoStr, CobrancaServicoTopicDTO.class);
				faturamentoService.incluirCobrancaServico(cobrancaServicoDto);
				System.out.println(cobrancaServicoDtoStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
				
	}

	
}
