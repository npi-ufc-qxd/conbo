package br.ufc.conbo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TipoBolsa {
	
	@Id
	@GeneratedValue
	private long id;
	private String nome;
	private String descricao;
	
	@OneToMany
	private List<Bolsa> bolsas;
	
 	public TipoBolsa() {
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "TipoBolsa [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", bolsas="
				+ bolsas + "]";
	}
	
	
}
