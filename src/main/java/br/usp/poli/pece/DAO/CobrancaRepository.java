package br.usp.poli.pece.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.usp.poli.pece.DTO.ConsumoServicoSaaSDTO;

public interface CobrancaRepository extends  MongoRepository<ConsumoServicoSaaSDTO, String> {
    

}