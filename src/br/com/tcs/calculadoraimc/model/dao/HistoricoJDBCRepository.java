package br.com.tcs.calculadoraimc.model.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.tcs.calculadoraimc.connection.ConexaoJDBC;
import br.com.tcs.calculadoraimc.model.business.IHistoricoDAO;
import br.com.tcs.calculadoraimc.model.models.HistoricoModel;
import br.com.tcs.calculadoraimc.utils.jdbc.DateUtils;
import br.com.tcs.calculadoraimc.utils.jdbc.JdbcUtils;

public class HistoricoJDBCRepository extends ConexaoJDBC implements IHistoricoDAO{

	public HistoricoJDBCRepository(String database, String userName, String password) {
		super(database, userName, password);
	}

	@Override
	public List<HistoricoModel> findAll() throws Exception {
		ResultSet rs=null;
		JdbcUtils jdbc=null;
		List<HistoricoModel> lista = new ArrayList<HistoricoModel>();
		try {
			jdbc = new JdbcUtils(this);
//			rs = sql.executeQuery("SELECT * FROM historico WHERE DATA_HORA BETWEEN ? AND ?", new Object[] {
//					LocalDateTime.of(2022, 04, 01, 22, 43, 00),
//					LocalDateTime.of(2022, 04, 30, 22, 45, 59)
//					});
			rs = jdbc.executeQuery("SELECT * FROM historico");
			while(rs.next()) {
				lista.add(new HistoricoModel(rs.getString("STATUS"),  DateUtils.stringToDate(rs.getString("DATA_HORA"))));
			}
		} catch (Exception e) {
			throw e;
		}finally {
			jdbc.closeResultSet(rs);
			jdbc.closeStatement();
			jdbc.closeConnection();
		}
		return lista;
	}

	@Override
	public void save(HistoricoModel entity) throws Exception {
		JdbcUtils jdbc=null;
		try {
			jdbc = new JdbcUtils(this);
			jdbc.executeQuery("INSERT INTO historico (STATUS, DATA_HORA) VALUES(?, ?)", new Object[] {
					entity.getStatus(), entity.getDataHora()
					});
		} catch (Exception e) {
			throw e;
		}finally {
			jdbc.closeStatement();
			jdbc.closeConnection();
		}
	}

}
