package br.ufc.conbo.model;

import java.io.File;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Frequencia {
	
	@Id
	@GeneratedValue
	private Long idFrequencia;
	private File arquivo;
	private Date mes;
	private Date dataEnvio;
	
	@OneToOne
	private Participacao participacao;
	
	public Frequencia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdFrequencia() {
		return idFrequencia;
	}
	public void setIdFrequencia(Long idFrequencia) {
		this.idFrequencia = idFrequencia;
	}
	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
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
}
