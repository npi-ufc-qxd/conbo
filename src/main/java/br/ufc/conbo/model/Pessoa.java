package br.ufc.conbo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue
	private long idPessoa;
	private String nome;
	private String email;
	
	@OneToMany
	private List<Bolsa> bolsas; 
	
	public Pessoa() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public long getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
