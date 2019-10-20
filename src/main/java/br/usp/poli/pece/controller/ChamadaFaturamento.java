package br.usp.poli.pece.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.usp.poli.pece.DTO.FaturamentoServicosDTO;
import br.usp.poli.pece.DTO.MensagemDTO;
import br.usp.poli.pece.service.FaturamentoService;

@RestController
public class ChamadaFaturamento {

	@Autowired
	private FaturamentoService faturamentoService;

	@GetMapping("/faturamento/{cliente}/{competencia}")
	public ResponseEntity obterFaturamentoServicos(@PathVariable(name = "cliente") String cliente,
			@PathVariable(name = "competencia") String competencia,
			@RequestParam(name = "servico", required = false) String servico) {
		try {

			List<FaturamentoServicosDTO> faturamento = faturamentoService.gerarFaturamento(cliente, competencia,
					servico);

			return new ResponseEntity<List<FaturamentoServicosDTO>>(faturamento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new MensagemDTO("Erro interno"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
