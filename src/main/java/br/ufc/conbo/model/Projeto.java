package br.ufc.conbo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

@Entity
public class Projeto {
	
	@Id
	@GeneratedValue
	private long id;
	private String nome;
	
	
	@Size(max=1024)
	private String descricao;
	
	@ManyToMany
	private List<Bolsa> bolsas; 
	
	public Projeto() {
		super();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Bolsa> getBolsas() {
		return bolsas;
	}

	public void setBolsas(List<Bolsa> bolsas) {
		this.bolsas = bolsas;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Projeto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", bolsas=" + bolsas
				+ ", getId()=" + getId() + ", getBolsas()=" + getBolsas() + ", getNome()=" + getNome()
				+ ", getDescricao()=" + getDescricao() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
