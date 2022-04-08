package br.com.tcs.calculadoraimc.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexaoJPA {
	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("CalculadoraIMC_PU");
	private final EntityManager em;
	
	public ConexaoJPA() {
		this.em = emf.createEntityManager();
	}
	
	public EntityManager getEntityManager() {
		return em;
	}
}
