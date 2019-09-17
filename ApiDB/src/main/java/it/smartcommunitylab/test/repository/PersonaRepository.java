package it.smartcommunitylab.test.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import it.smartcommunitylab.test.model.Persona;

@Repository
public interface PersonaRepository extends MongoRepository<Persona, String>, PersonaRepositoryCustom {
	//@Query("{tags:?0}")
	List<Persona> findByTags(String tags);
	List<Persona> findByNomeOrCognome(String text);
}
