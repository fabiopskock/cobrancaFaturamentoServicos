package br.usp.poli.pece.listener;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

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
			if(!StringUtils.containsIgnoreCase(cobrancaServicoDtoStr, "jvm") 
					) {
				ObjectMapper mapper = new ObjectMapper();
				CobrancaServicoTopicDTO cobrancaServicoDto =
					mapper.readValue(cobrancaServicoDtoStr, CobrancaServicoTopicDTO.class);
				if(cobrancaServicoDto.getStatus() == 200) {
					System.out.println("Mensagem Recebida: "+cobrancaServicoDtoStr);
					faturamentoService.incluirCobrancaServico(cobrancaServicoDto);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
				
	}

	
}
