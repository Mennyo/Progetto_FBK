package it.smartcommunitylab.test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import it.smartcommunitylab.test.model.Persona;

public class PersonaRepositoryCustomImpl implements PersonaRepositoryCustom {
	//chiede all'infrastruttura di dare una referenza a un oggetto di un certo tipo
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<Persona> searchPersona(String regex, Pageable pageable) {
		Criteria criteria = new Criteria();
		//controllo se esiste nella stringa nome-cognome 
		//o codice fiscale e che la stringa non sia vuota
		if((regex != null) && (!regex.trim().isEmpty())) {
			criteria = criteria.orOperator(Criteria.where("nome").regex(regex, "i"),
					Criteria.where("cognome").regex(regex, "i"),
					Criteria.where("codiceFiscale").regex(regex, "i"));
		}
		Query query = new Query(criteria);
		if(pageable != null) {
			query = query.with(pageable);
		}
		return mongoTemplate.find(query, Persona.class);
	}
	
}
