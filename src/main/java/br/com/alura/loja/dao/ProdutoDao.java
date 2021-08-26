package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.entity.Produto;

public class ProdutoDao {

	private EntityManager entityManager;

	public ProdutoDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(Produto entity) {
		this.entityManager.persist(entity);
	}
	
	public void remover(Produto entity) {
		this.entityManager.remove(entity);
	}
	
	public Produto buscarPorId(Long id) {
		return entityManager.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos(){
		String jpql = "SELECT p from Produto p";
		return entityManager.createQuery(jpql).getResultList();
	}
}	
