package br.com.tcs.calculadoraimc.model.business;

import java.util.List;

import br.com.tcs.calculadoraimc.model.models.HistoricoModel;

public interface IHistoricoDAO {
	public List<HistoricoModel> findAll() throws Exception;
	public void save(HistoricoModel entity) throws Exception;
}
