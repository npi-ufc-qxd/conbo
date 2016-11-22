package br.ufc.conbo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Participacao {
	
	@Id
	@GeneratedValue
	private long id;
	private Date dataInicio;
	private Date dataFim;
	private Date dataExpectFim;
	private boolean status;
	private boolean remuneracao;
	
	@OneToOne
	private Aluno aluno;
	
	@OneToMany
	private List<Frequencia> frequencias;
	
	@OneToOne
	private Bolsa bolsa;
	
	public Participacao() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataExpectFim() {
		return dataExpectFim;
	}

	public void setDataExpectFim(Date dataExpectFim) {
		this.dataExpectFim = dataExpectFim;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isRemuneracao() {
		return remuneracao;
	}

	public void setRemuneracao(boolean remuneracao) {
		this.remuneracao = remuneracao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Frequencia> getFrequencias() {
		return frequencias;
	}

	public void setFrequencias(List<Frequencia> frequencias) {
		this.frequencias = frequencias;
	}

	public Bolsa getBolsa() {
		return bolsa;
	}

	public void setBolsa(Bolsa bolsa) {
		this.bolsa = bolsa;
	}
	
	
}
