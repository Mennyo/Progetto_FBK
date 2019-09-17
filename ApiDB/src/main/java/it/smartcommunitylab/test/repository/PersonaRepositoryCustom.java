package it.smartcommunitylab.test.repository;

import java.util.*;
import org.springframework.data.domain.Pageable;
import it.smartcommunitylab.test.model.Persona;

public interface PersonaRepositoryCustom {
	List<Persona> searchPersona(String regex, Pageable pageable);
}	
