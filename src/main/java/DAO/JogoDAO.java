package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Jogo;
import util.JPAUtil;

public class JogoDAO {

	public static void salvar(Jogo j) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(j);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void editar(Jogo j) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(j);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void excluir(Jogo j) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		j = em.find(Jogo.class, j.getId());
		em.remove(j);
		em.getTransaction().commit();
		em.close();
	}
	
	public static List<Jogo> listar() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query query = em.createQuery("select j from Jogo j");
		List<Jogo> jogo = query.getResultList();
		em.close();
		return jogo;
	}
	
}
