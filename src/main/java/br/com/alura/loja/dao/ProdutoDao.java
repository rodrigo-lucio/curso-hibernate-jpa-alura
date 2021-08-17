package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.entity.Produto;

public class ProdutoDao {

	private EntityManager entityManager;

	public ProdutoDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Produto produto) {
		this.entityManager.persist(produto);
	}
}
