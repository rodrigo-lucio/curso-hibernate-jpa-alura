package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.entity.Categoria;

public class CategoriaDao {

	private EntityManager entityManager;

	public CategoriaDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(Categoria entity) {
		this.entityManager.persist(entity);
	}
	
	public void remover(Categoria entity) {
		this.entityManager.remove(entity);
	}

}
