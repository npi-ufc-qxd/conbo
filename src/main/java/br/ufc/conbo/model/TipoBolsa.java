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
	private long idTipoBolsa;
	private String nome;
	private String descricao;
	
	@OneToMany
	private List<Bolsa> bolsas;
	
 	public TipoBolsa() {
		super();
	}
	
	public long getIdTipoBolsa() {
		return idTipoBolsa;
	}
	public void setIdTipoBolsa(long idTipoBolsa) {
		this.idTipoBolsa = idTipoBolsa;
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
