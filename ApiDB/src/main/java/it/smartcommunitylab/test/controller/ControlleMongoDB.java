package it.smartcommunitylab.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.smartcommunitylab.test.model.Persona;
import it.smartcommunitylab.test.repository.PersonaRepository;

@Controller
public class ControlleMongoDB {
	//
	@Autowired
	private PersonaRepository personaRepository;
	
	//OTTENGO LA LISTA DELLE PERSONE
	
	@GetMapping(value = "/persone")
	public @ResponseBody List<Persona> list() {
		return personaRepository.findAll();
	}
	
	//OTTIENI UNA PERSONA DI ID
	@GetMapping(value = "/persona/{id}")
	public @ResponseBody Persona get(@PathVariable String id){
		Optional<Persona> optional = personaRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}
	}
	
	@PostMapping(value="/persona")
	public @ResponseBody Persona add(@RequestBody Persona persona) {
		persona.setId(UUID.randomUUID().toString());
		personaRepository.save(persona);
		return persona;
	}
	
	//REFRESH DI TUTTE LE PERSONE
	@PutMapping(value = "/persona/{id}")
	public ResponseEntity<Persona> update (@PathVariable String id, @RequestBody Persona persona) {
		Optional<Persona> personaDb = personaRepository.findById(id);
		if(personaDb.isPresent()) {
			persona.setId(id);
			personaRepository.save(persona);
			return new ResponseEntity<Persona>(persona, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
		}
	}
	
	//ESEGUO UNA DELETE SULL ID DELLA PERSONA
	@DeleteMapping(value = "/persona/{id}")
	public ResponseEntity<Persona> delete(@PathVariable String id) {
		Optional<Persona> personaDb = personaRepository.findById(id);
		if(personaDb.isPresent()) {
			personaRepository.delete(personaDb.get());
			return new ResponseEntity<Persona>(personaDb.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
		}
	}
	//ESEGUO UNA DELETE SU TUTTO
	@DeleteMapping(value = "/persone")
	public @ResponseBody void deleteAll() {
		personaRepository.deleteAll();
	}
	
	@GetMapping(value = "/persone/tag/{tags}")
	public @ResponseBody List<Persona> searchByTags(@PathVariable String tags){
		List<Persona> result = new ArrayList<Persona>();
		for (Persona p : personaRepository.findAll()) {
			if(p.getTags().contains(tags)) {
				result.add(p);
			}
		}
		return result;
	}
	
	@GetMapping(value = "/persone/tag2/{tags}")
	public @ResponseBody List<Persona> searchByTag(@PathVariable String tags){
		return personaRepository.findByTags(tags);
	}
	
	//cerca per stringa (codice fiscale, nome, cognome)
	@GetMapping("persone/regex/{regex}")
	public @ResponseBody List<Persona> searchByRegex(@PathVariable String regex, @RequestParam(required=false) Pageable pageable) {
		return personaRepository.searchPersona(regex, pageable);
	}
}
