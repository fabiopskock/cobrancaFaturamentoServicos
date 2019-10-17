package br.usp.poli.pece.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/faturamento/{cliente}/{mes}", 
    				method = RequestMethod.GET
    				)
    public ResponseEntity obterFaturamentoServicos(
    		@PathVariable(name = "cliente") String cliente,
    		@PathVariable(name = "mes") String mes,
    		@RequestParam(name = "servico", required = false) String servico
    		) {
    	try {
    		
    		FaturamentoServicosDTO faturamento = faturamentoService.gerarFaturamento(cliente,mes,servico);
    		
    		return new ResponseEntity<FaturamentoServicosDTO>(faturamento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new MensagemDTO("Erro interno") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
    }
	
}
