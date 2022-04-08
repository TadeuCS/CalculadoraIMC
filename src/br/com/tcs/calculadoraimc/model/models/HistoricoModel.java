package br.com.tcs.calculadoraimc.model.models;

import java.time.LocalDateTime;
import java.util.Date;

import br.com.tcs.calculadoraimc.model.entities.Historico;
import br.com.tcs.calculadoraimc.utils.jdbc.DateUtils;

public class HistoricoModel extends Historico {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HistoricoModel(String status, Date dataHora) {
		super(status, dataHora);
	}
	
	public HistoricoModel(String status) {
		super(status, DateUtils.asDate(LocalDateTime.now()));
	}
}
