package br.usp.poli.pece.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.usp.poli.pece.DTO.CobrancaServicoTopicDTO;
import br.usp.poli.pece.DTO.FaturamentoServicosDTO;

public interface CobrancaRepository extends MongoRepository<CobrancaServicoTopicDTO, String> {
    
	/*
	 * @Query("" + "db.cobrancaServicos.aggregate([   " + "{$match:  " + "	{  " +
	 * "		competencia: \"201910\", " + "		remoteAddress:\"127.0.0.1\" " +
	 * "	} " + "} ,  " + "{  $group :   " + "	{  _id :  " + "		{ " +
	 * "			uri:\"$uri\", " + "			competencia:\"$competencia\" " +
	 * "		} " + "		, count: { $sum: 1 }  " + "	}    " + "}, " +
	 * "{$lookup: " + "        { " + "          from: \"servicos\", " +
	 * "          localField: \"_id.uri\", " +
	 * "          foreignField: \"servico_uri\", " + "          as: \"servicos\" " +
	 * "        } " + "}, " + "{   $unwind:\"$servicos\" }, " + "{    " +
	 * "        $project:{ " + "		    competencia : \"$_id.competencia\", " +
	 * "            uri : 1, " + "            count : 1, " +
	 * "            valor : \"$servicos.valor\", " +
	 * "            servico_uri : \"$servicos.servico_uri\", " +
	 * "			servico : \"$servicos.servico\", " +
	 * "			valor_total_servico: {$sum:  {$multiply: [\"$servicos.valor\", \"$count\" ]}} "
	 * + "        }  " + "    } " + " " + " ] )") List<FaturamentoServicosDTO>
	 * findbyMesCliente(String mes, String cliente)
	 */
	  
}