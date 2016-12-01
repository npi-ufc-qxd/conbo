package br.ufc.conbo.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Frequencia {
	
	@Id
	@GeneratedValue
	private Long idFrequencia;
	private String caminhoArquivo;
	@DateTimeFormat(pattern = "YYYY-MM")
	private Date mes;
	private Date dataEnvio;
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToOne
	private Participacao participacao;
	
	public Frequencia() {
		super();
	}

	public Long getIdFrequencia() {
		return idFrequencia;
	}
	public void setIdFrequencia(Long idFrequencia) {
		this.idFrequencia = idFrequencia;
	}
	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}

	public Date getMes() {
		return mes;
	}

	public void setMes(Date mes) {
		this.mes = mes;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Participacao getParticipacao() {
		return participacao;
	}

	public void setParticipacao(Participacao participacao) {
		this.participacao = participacao;
	}
	
	
}
