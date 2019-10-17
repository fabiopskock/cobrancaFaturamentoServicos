package br.usp.poli.pece.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.usp.poli.pece.DTO.CobrancaServicoTopicDTO;

public interface CobrancaRepository extends MongoRepository<CobrancaServicoTopicDTO, String> {
    
	  @Query("{ 'mes' : ?0 }")
	  List<CobrancaServicoTopicDTO> findbyMes(String mes);
	  
	  @Query("{ 'mes' : ?0 , 'cliente' : ?1 }")
	  List<CobrancaServicoTopicDTO> findbyMesCliente(String mes, String cliente);
	  
	  @Query("{ 'mes' : ?0 , 'cliente' : ?1 , 'servico' : ?2 }")
	  List<CobrancaServicoTopicDTO> findbyMesClienteServico(String mes, String cliente, String servico);
	  
}