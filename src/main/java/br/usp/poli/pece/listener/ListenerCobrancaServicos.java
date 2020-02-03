package br.usp.poli.pece.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

import br.usp.poli.pece.controller.RegistrarConsumoServicosSaaS;


@RestController
public class ListenerCobrancaServicos {
	
	@Autowired
	private RegistrarConsumoServicosSaaS registrarCobrancaController;
	
	
	
	@KafkaListener(groupId = "groupID-Listener", 
					topics = "test",
					containerFactory = "kafkaListenerContainerFactory")
	public void obterMsgFromTopic(String cobrancaServicoDtoStr) {
				
		try {
			registrarCobrancaController.registrarConsumo(cobrancaServicoDtoStr);
		} catch (Exception e) {
			e.printStackTrace();
		} 
				
	}


	
}
