package ia.desafio.backend.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import ia.desafio.backend.model.Inscripcion;

@Repository
public class DynamoDbRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDbRepository.class);

	@Autowired
	private DynamoDBMapper mapper;

	public Boolean insertInscripcion(Inscripcion obj) {
		try{
			mapper.save(obj);
			return true;
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}
	}

	public Inscripcion getInscripcionById(String id) {
		return mapper.load(Inscripcion.class, id);
	}


}