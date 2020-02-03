package br.usp.poli.pece.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.usp.poli.pece.DTO.ConsumoServicoSaaSDTO;
import br.usp.poli.pece.DTO.FaturamentoServicosDTO;
import br.usp.poli.pece.DTO.MensagemDTO;
import br.usp.poli.pece.service.CobrancaServicosSaaSDAO;

@RestController
public class FaturamentoController {

	@Autowired
	private CobrancaServicosSaaSDAO faturamentoService;

	@GetMapping("/faturamento/{cliente}/{competencia}")
	public ResponseEntity obterFaturamentoServicos(
			@PathVariable(name = "cliente") String cliente,
			@PathVariable(name = "competencia") String competencia,
			@RequestParam(name = "servico", required = false) String servico) {
		try {

			FaturamentoServicosDTO faturamento = faturamentoService.gerarFaturamento(cliente, competencia,
					servico);	
			
			return new ResponseEntity<FaturamentoServicosDTO>(faturamento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MensagemDTO>(new MensagemDTO("Erro interno"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/faturamento/{cliente}/{competencia}/servicos")
	public ResponseEntity obterCobrancasServicos(
			@PathVariable(name = "cliente") String cliente,
			@PathVariable(name = "competencia") String competencia,
			@RequestParam(name = "servico", required = false) String servico) {
		try {

			List<ConsumoServicoSaaSDTO> cobrancas = faturamentoService.listarCobrancaServico();	

			
			return new ResponseEntity<List<ConsumoServicoSaaSDTO>>(cobrancas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MensagemDTO>(new MensagemDTO("Erro interno"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
