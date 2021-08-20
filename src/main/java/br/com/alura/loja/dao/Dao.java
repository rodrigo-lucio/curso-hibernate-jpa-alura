package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.entity.Produto;

public class Dao<T> {

	private EntityManager entityManager;

	public Dao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(T entity) {
		this.entityManager.persist(entity);
	}
	
	public void remover(T entity) {
		this.entityManager.remove(entity);
	}

}
