package br.ufc.conbo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Participacao {
	
	@Id
	@GeneratedValue
	private long idParticipacao;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date dataInicio;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date dataFim;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date dataExpectFim;
	private boolean status;
	private boolean remuneracao;
	private String observacao;
	
	@OneToOne
	private Aluno aluno;
	
	@OneToMany
	private List<Frequencia> frequencias;
	
	@OneToOne
	private Bolsa bolsa;
	
	public Participacao() {
		super();
	}
	
	public long getIdParticipacao() {
		return idParticipacao;
	}

	public void setIdParticipacao(long idParticipacao) {
		this.idParticipacao = idParticipacao;
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

	public boolean isRemuneracao() {
		return remuneracao;
	}

	
	
	@Override
	public String toString() {
		return "Participacao [idParticipacao=" + idParticipacao + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim
				+ ", dataExpectFim=" + dataExpectFim + ", status=" + status + ", remuneracao=" + remuneracao
				+ ", observacao=" + observacao + ", aluno=" + aluno + ", frequencias=" + frequencias + ", bolsa="
				+ bolsa + "]";
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
