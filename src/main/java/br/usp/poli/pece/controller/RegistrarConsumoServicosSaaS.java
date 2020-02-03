package br.usp.poli.pece.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.usp.poli.pece.DTO.ConsumoServicoSaaSDTO;
import br.usp.poli.pece.service.CobrancaServicosSaaSDAO;



@Component
public class RegistrarConsumoServicosSaaS {
	
	@Autowired
	private CobrancaServicosSaaSDAO cobrancaServicosSaaSDAO;
	
	public void registrarConsumo(String cobrancaServicoDtoStr) throws IOException, JsonParseException, JsonMappingException {
		if( validarMsgTopico(cobrancaServicoDtoStr)	) {
			ConsumoServicoSaaSDTO cobrancaServicoDto = converterToCobrancaServicoTopicoDTO(cobrancaServicoDtoStr);
			if(validaSucessoConsumo(cobrancaServicoDto)) {
				System.out.println("Mensagem Recebida: "+cobrancaServicoDtoStr);
				cobrancaServicosSaaSDAO.registrarCobrancaServico(cobrancaServicoDto);

			}
		}
	}

	private ConsumoServicoSaaSDTO converterToCobrancaServicoTopicoDTO(String cobrancaServicoDtoStr)
			throws IOException, JsonParseException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		ConsumoServicoSaaSDTO cobrancaServicoDto =
				mapper.readValue(cobrancaServicoDtoStr, ConsumoServicoSaaSDTO.class);
		return cobrancaServicoDto;
	}

	private boolean validaSucessoConsumo(ConsumoServicoSaaSDTO cobrancaServicoDto) {
		return cobrancaServicoDto.getStatus() == 200;
	}

	private boolean validarMsgTopico(String cobrancaServicoDtoStr) {
		return !StringUtils.containsIgnoreCase(cobrancaServicoDtoStr, "jvm");
	}

	
}
