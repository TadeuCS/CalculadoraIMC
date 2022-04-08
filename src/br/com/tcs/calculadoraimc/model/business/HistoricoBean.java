package br.com.tcs.calculadoraimc.model.business;

import java.util.ArrayList;
import java.util.List;

import br.com.tcs.calculadoraimc.model.entities.Historico;
import br.com.tcs.calculadoraimc.model.models.HistoricoModel;

public class HistoricoBean {
	private final IHistoricoDAO dao;
	
	public HistoricoBean(IHistoricoDAO dao) {
		this.dao=dao;
	}
	
	public List<HistoricoModel> listaTodos() throws Exception{
		try {
			return dao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao listar os Históricos do Banco de Dados");
		}
	}
	
	public void salva(HistoricoModel model) throws Exception{
		try {
			dao.save(model);
		} catch (Exception e) {
			throw new Exception("Erro ao salvar o Histórico no Banco de Dados");
		}
	}
}
