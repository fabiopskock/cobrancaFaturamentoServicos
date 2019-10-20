package br.usp.poli.pece.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DeferredImportSelector.Group;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Multiply;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation.GroupOperationBuilder;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.web.ProjectedPayload;
import org.springframework.stereotype.Service;

import com.mongodb.client.model.Aggregates;

import br.usp.poli.pece.DAO.CobrancaRepository;
import br.usp.poli.pece.DTO.CobrancaServicoTopicDTO;
import br.usp.poli.pece.DTO.FaturamentoServicosDTO;
@Service
public class FaturamentoService {

	@Autowired
	private CobrancaRepository cobrancaRepository;
	
	@Autowired
    private MongoTemplate mongoTemplate;

	
	public List<FaturamentoServicosDTO> gerarFaturamento(String cliente, String competencia, String servico) {
		
		MatchOperation whereMatch = Aggregation.match(
  				Criteria.where("remoteAddress").is(cliente)
  				);
		MatchOperation whereMatch2 = Aggregation.match(
				Criteria.where("competencia").is(competencia)
  				);
  		
  		GroupOperation groupAgg = Aggregation.group("uri","remoteAddress")
  				.count().as("qtChamadasService");
  		
  		LookupOperation lookupOperation = LookupOperation.newLookup()
  			    .from("servicos")
  			    .localField("_id.uri")
  			    .foreignField("servico_uri")
  			    .as("servicos");
  		
  		UnwindOperation unwindOperation = Aggregation.unwind("servicos");
  		
  		ProjectionOperation projection = Aggregation.project(
  				"uri",
  				"qtChamadasService",
  				"$servicos.valor",
  				"$servicos.servico_uri",
  				"$servicos.servico"
  				);
  		
  		
		Aggregation agg = Aggregation.newAggregation(
				whereMatch
				,whereMatch2
				,groupAgg
				,lookupOperation
				,unwindOperation
				,projection
			);
			
		AggregationResults output = mongoTemplate.aggregate(agg,"cobrancaServicos",HashMap.class);
				
			List<HashMap> list = output.getMappedResults();
			return null;
		
		
		//return cobrancaRepository.findbyMesCliente(mes, cliente);
	}

	public void incluirCobrancaServico(CobrancaServicoTopicDTO cobrancaServicoDto) {
		
		//System.out.println(cobrancaServicoDto.toString());
		
		cobrancaRepository.insert(cobrancaServicoDto);
		
		//List<CobrancaServicoTopicDTO> l = cobrancaRepository.findAll(); 
		//l.size();
		//cobrancaRepository.save(cobrancaServicoDto);
	}

}
