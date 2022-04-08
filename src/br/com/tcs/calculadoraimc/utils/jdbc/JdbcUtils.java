package br.com.tcs.calculadoraimc.utils.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.tcs.calculadoraimc.connection.ConexaoJDBC;
import br.com.tcs.calculadoraimc.utils.QueryBuilder;

public class JdbcUtils {
	
	private final ConexaoJDBC conexao;
	private static Statement st;
	
	public JdbcUtils(ConexaoJDBC conexao) throws Exception {
		this.conexao = conexao;
	}
	
	public Statement openStatement() throws SQLException{
		try {
			return openConection().createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("Erro ao Criar estado banco de dados!");
		}
	}
	
	private Connection openConection() throws SQLException {
		try {
			if(conexao.getConn()==null || (conexao.getConn()!=null && conexao.getConn().isClosed())) {
				conexao.openConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("Erro ao abrir conexão com banco de dados!");
		}
		return conexao.getConn();
	}
	
	public void closeConnection() throws Exception{
		try {
			if(conexao.getConn()!=null && !conexao.getConn().isClosed()) {
				conexao.getConn().close();
			}
		} catch (Exception e) {
			throw new Exception("Erro ao fechar a conexão!");
		}
	}
	
	public ResultSet executeQuery(String query) throws Exception {
		return executeQuery(query, new Object[] {});
	}
	
	public ResultSet executeQuery(String query, Object[] params) throws Exception {
		query = QueryBuilder.getQuery(query, params);
		st = openStatement();
		return st.executeQuery(query);
	}
	
	public void executeUpdate(String query, Object[] params) throws Exception {
		query = QueryBuilder.getQuery(query, params);
		st = openStatement();
		st.executeUpdate(query);
	}
	
	public void closeStatement() throws Exception{
		closeStatement(st);
	}
	
	public void closeStatement(Statement st) throws Exception {
		try {
			if(st!=null && !st.isClosed()) {
				st.close();
			}
			st=null;
		} catch (Exception e) {
			throw new Exception("Erro ao fechar a Declaração!");
		}
	}
	
	public void closeResultSet(ResultSet rs) throws Exception {
		try {
			if(rs!=null && !rs.isClosed()) {
				rs.close();
			}
			rs=null;
		} catch (Exception e) {
			throw new Exception("Erro ao fechar o Conjunto de Resultados!");
		}
	}

}
