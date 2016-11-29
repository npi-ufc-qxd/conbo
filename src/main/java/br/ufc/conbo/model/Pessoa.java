package br.ufc.conbo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Pessoa {
	
	
	@Id
	@GeneratedValue
	private long id;
	private String nome;
	private String email;
	
	@ManyToMany
	@JoinTable(name="bolsa_responsaveis", 
            joinColumns=  @JoinColumn( name = "idPessoa"), 
            inverseJoinColumns= @JoinColumn(name = "idBolsa") )
	private List<Bolsa> bolsas; 
	
	public Pessoa() {
		super();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	
	
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", email=" + email + ", bolsas=" + bolsas + "]";
	}
	
}
