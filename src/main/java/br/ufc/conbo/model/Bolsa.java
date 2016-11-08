package br.ufc.conbo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Bolsa {
	
	@Id
	@GeneratedValue
	private long idBolsa;
	private String nome;
	private Double valor;
	private int ano;
	private boolean frequencia;
	private int vagas;
	private String observacao;
	private boolean status;
	private boolean folhaPagamento;
	
	@OneToMany
	private List<Participacao> participacoes;
	
	@OneToOne
	@JoinColumn(nullable=true)
	private Projeto projeto;
	
	@OneToOne
	private TipoBolsa tipoBolsa;
	
	@OneToMany
	private List<Pessoa> responsaveis;
	
	public Bolsa() {
		super();		
	}

	public long getIdBolsa() {
		return idBolsa;
	}

	public void setIdBolsa(long idBolsa) {
		this.idBolsa = idBolsa;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Participacao> getParticipacoes() {
		return participacoes;
	}

	public void setParticipacoes(List<Participacao> participacoes) {
		this.participacoes = participacoes;
	}
	
	public int countBolsistasAtivos(){
		int count = 0;
		
		for(Participacao participacao: getParticipacoes()){
			if(participacao.isStatus()){
				count++;
			}
		}
		
		return count;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public TipoBolsa getTipoBolsa() {
		return tipoBolsa;
	}

	public void setTipoBolsa(TipoBolsa tipoBolsa) {
		this.tipoBolsa = tipoBolsa;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public boolean isFrequencia() {
		return frequencia;
	}

	public void setFrequencia(boolean frequencia) {
		this.frequencia = frequencia;
	}

	public List<Pessoa> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Pessoa> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isFolhaPagamento() {
		return folhaPagamento;
	}

	public void setFolhaPagamento(boolean folhaPagamento) {
		this.folhaPagamento = folhaPagamento;
	}
}
