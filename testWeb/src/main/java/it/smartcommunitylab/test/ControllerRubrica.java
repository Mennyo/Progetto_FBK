package it.smartcommunitylab.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerRubrica {
	private Map<String, Persona> dataMap = new HashMap<>();
	
	//OTTENGO LA LISTA DELLE PERSONE
	@GetMapping(value = "/persone")
	public @ResponseBody List<Persona> list() {
		return new ArrayList<>(dataMap.values());
	}
	
	//OTTIENI UNA PERSONA DI ID
	@GetMapping(value = "/persona/{id}")
	public @ResponseBody Persona get(@PathVariable String id){
		return dataMap.get(id);
	}
	
	@PostMapping(value="/persona")
	public @ResponseBody Persona add(@RequestBody Persona persona) {
		persona.setId(UUID.randomUUID().toString());
		dataMap.put(persona.getId(), persona);
		return persona;
	}
	
	//REFRESH DI TUTTE LE PERSONE
	@PutMapping(value = "/persona/{id}")
	public ResponseEntity<Persona> update (@PathVariable String id, @RequestBody Persona persona) {
		Persona personaMap = dataMap.get(id);
		if(personaMap != null) {
			persona.setId(id);
			dataMap.put(persona.getId(), persona);
			return new ResponseEntity<Persona>(persona, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
		}
	}
	
	//ESEGUO UNA DELETE SULL ID DELLA PERSONA
	@DeleteMapping(value = "/persona/{id}")
	public ResponseEntity<Persona> delete(@PathVariable String id) {
		Persona personaMap = dataMap.get(id);
		if(personaMap != null) {
			dataMap.remove(id);
			return new ResponseEntity<Persona>(personaMap, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
		}
	}
	//ESEGUO UNA DELETE SU TUTTO
	@DeleteMapping(value = "/persone")
	public @ResponseBody void deleteAll() {
		dataMap.clear();
	}
	
	@GetMapping(value = "/persone/{tags}")
	public @ResponseBody List<Persona> search(@PathVariable String tags){
		List<Persona> result = new ArrayList<>();
		for (Persona p : dataMap.values()) {
			if(p.getTags().contains(tags)) {
				result.add(p);
			}
		}
		return result;
	}
}
