package br.usp.poli.pece.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import br.usp.poli.pece.DAO.CobrancaRepository;
import br.usp.poli.pece.DTO.ConsumoServicoSaaSDTO;
import br.usp.poli.pece.DTO.FaturamentoServicosDTO;
import br.usp.poli.pece.DTO.ServicosFaturamento;

@Service
public class CobrancaServicosSaaSDAO {

	@Autowired
	private CobrancaRepository cobrancaRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public FaturamentoServicosDTO gerarFaturamento(String cliente, String competencia, String servico) {

		MatchOperation whereMatch = Aggregation.match(Criteria.where("remoteAddress").is(cliente));
		MatchOperation whereMatch2 = Aggregation.match(Criteria.where("competencia").is(competencia));

		GroupOperation groupAgg = Aggregation.group("uri", "remoteAddress").count().as("qtChamadasService");

		LookupOperation lookupOperation = LookupOperation.newLookup().from("servicos").localField("_id.uri")
				.foreignField("servico_uri").as("servicos");

		UnwindOperation unwindOperation = Aggregation.unwind("servicos");


		ProjectionOperation projection = Aggregation.project("uri", "qtChamadasService", "$servicos.valor",
				"$servicos.servico_uri", "$servicos.servico_name");

		Aggregation agg = Aggregation.newAggregation(whereMatch, whereMatch2, groupAgg, lookupOperation,
				unwindOperation, projection);

		AggregationResults<ServicosFaturamento> output = mongoTemplate.aggregate(agg, "cobrancaServicos", ServicosFaturamento.class);

		FaturamentoServicosDTO faturamento = new FaturamentoServicosDTO(output.getMappedResults());
		return faturamento;

	}

	public void registrarCobrancaServico(ConsumoServicoSaaSDTO cobrancaServicoDto) {

		cobrancaRepository.insert(cobrancaServicoDto);

	}

	public List<ConsumoServicoSaaSDTO> listarCobrancaServico() {

		return cobrancaRepository.findAll();

	}

}
