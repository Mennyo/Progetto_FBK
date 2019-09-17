package it.smartcommunitylab.test.model;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;

import java.util.*;

public class Persona {
	@Id
	private String id; //DEFINISCO LE VARIABILI CHE ANDRO A RIEMPIRE CON LA POST DOPO
	private String codiceFiscale; 
	private String nome;
	private String cognome;
	private Date dataNascita;
	private List<String> tags = new ArrayList<>();
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
