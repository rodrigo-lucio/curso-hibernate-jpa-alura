package br.com.alura.loja.dao;

import java.math.BigDecimal;
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

	public List<Produto> buscarTodos() {
		String jpql = "SELECT p from Produto p";
		return entityManager.createQuery(jpql).getResultList();
	}

	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p from Produto p where p.nome = :nome";
		return entityManager.createQuery(jpql)
							.setParameter("nome", nome)
							.getResultList();
	}
	public List<Produto> buscarPorNomeDaCategoria(String nome) {
		String jpql = "SELECT p from Produto p where p.categoria.nome = :nome"; //ja faz o join automatico com o p.categoria.nome
		return entityManager.createQuery(jpql)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public BigDecimal buscarPrecoPorNome(String nome) {
		String jpql = "SELECT p.preco from Produto p where p.nome = :nome";
		return (BigDecimal) entityManager.createQuery(jpql)
				.setParameter("nome", nome)
				.getSingleResult();
	}
}
