package br.usp.poli.pece.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usp.poli.pece.DAO.CobrancaRepository;
import br.usp.poli.pece.DTO.CobrancaServicoTopicDTO;
import br.usp.poli.pece.DTO.FaturamentoServicosDTO;

@Service
public class FaturamentoService {

	@Autowired
	private CobrancaRepository cobrancaRepository;
	
	public FaturamentoServicosDTO gerarFaturamento(String cliente, String mes, String servico) {
		
		//cobrancaRepository.
		
		
		return null;
	}

	public void incluirCobrancaServico(CobrancaServicoTopicDTO cobrancaServicoDto) {
		
		//System.out.println(cobrancaServicoDto.toString());
		
		cobrancaRepository.insert(cobrancaServicoDto);
		
		//List<CobrancaServicoTopicDTO> l = cobrancaRepository.findAll(); 
		//l.size();
		//cobrancaRepository.save(cobrancaServicoDto);
	}

}
