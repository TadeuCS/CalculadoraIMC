package br.com.tcs.calculadoraimc.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.tcs.calculadoraimc.connection.ConexaoJPA;
import br.com.tcs.calculadoraimc.model.business.IHistoricoDAO;
import br.com.tcs.calculadoraimc.model.entities.Historico;
import br.com.tcs.calculadoraimc.model.models.HistoricoModel;

public class HistoricoJPARepository extends ConexaoJPA implements IHistoricoDAO{
	
	public void save(HistoricoModel model) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			Historico entity = new Historico(model.getStatus(), model.getDataHora());
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}
	
	public List<HistoricoModel> findAll() {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			Query query = em.createNamedQuery("Historico.findAll", HistoricoModel.class);
			em.getTransaction().commit();
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return new ArrayList<>();
		}
	}
}
