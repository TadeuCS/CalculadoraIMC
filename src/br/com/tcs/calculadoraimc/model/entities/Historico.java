package br.com.tcs.calculadoraimc.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the historico database table.
 * 
 */
@Entity
@NamedQuery(name="Historico.findAll", query="SELECT h FROM Historico h")
public class Historico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codhistorico;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_HORA")
	private Date dataHora;

	private String status;

	public Historico() {
	}
	
	public Historico(String status, Date dataHora) {
		this.status=status;
		this.dataHora=dataHora;
	}

	public int getCodhistorico() {
		return this.codhistorico;
	}

	public void setCodhistorico(int codhistorico) {
		this.codhistorico = codhistorico;
	}

	public Date getDataHora() {
		return this.dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}