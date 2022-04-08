package br.com.tcs.calculadoraimc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoJDBC {
	private final String DRIVE = "com.mysql.cj.jdbc.Driver";
	private final String host, database, userName, password;
	private final int port;
	private Connection conn=null;
	
	public Connection getConn() {
		return conn;
	}

	public ConexaoJDBC(String host, int port, String database, String userName, String password) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.userName = userName;
		this.password = password;
	}

	public ConexaoJDBC(String host, String database, String userName, String password) {
		this.host = host;
		this.port = 3306;
		this.database = database;
		this.userName = userName;
		this.password = password;
	}

	public ConexaoJDBC(String database, String userName, String password) {
		this.host = "localhost";
		this.port = 3306;
		this.database = database;
		this.userName = userName;
		this.password = password;
	}
	
	public Connection openConnection() throws Exception {
		String url = "jdbc:mysql://"+host+":"+port+"/"+database+"?" +
				"user="+userName+"&password="+password;
		try {
			initDrive();
			conn = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			throw e;
		}catch (SQLException e) {
			System.out.println(url);
			throw e;
		}
		return conn;
	}
	
	private void initDrive() throws Exception {
		try {
			Class.forName(DRIVE).newInstance();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Erro ao instanciar o drive: "+DRIVE);
		}
	}
	
}
