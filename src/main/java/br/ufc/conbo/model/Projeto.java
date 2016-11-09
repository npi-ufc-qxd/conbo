package br.ufc.conbo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Projeto {
	
	@Id
	@GeneratedValue
	private long idProjeto;
	private String nome;
	private String descricao;
	
	@OneToMany
	private List<Bolsa> bolsas; 
	
	public Projeto() {
		super();
	}
	
	public long getIdProjeto() {
		return idProjeto;
	}
	public void setIdProjeto(long idProjeto) {
		this.idProjeto = idProjeto;
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
}
