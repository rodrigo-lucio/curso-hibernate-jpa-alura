package br.com.alura.loja.entity.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.Dao;
import br.com.alura.loja.entity.Categoria;
import br.com.alura.loja.entity.Produto;
import br.com.alura.loja.entity.util.JPAUtil;

public class CadastroProduto {

	public static void main(String[] args) {

		Produto celular = new Produto();
		celular.setNome("Xiaomi Redmi");
		celular.setDescricao("Muito legal");
		celular.setPreco(new BigDecimal("800"));

		Categoria categoria = new Categoria("Celulares");
		celular.setCategoria(categoria);

		EntityManager entityManager = JPAUtil.getEntityManager();

		Dao<Object> dao = new Dao<Object>(entityManager);

		entityManager.getTransaction().begin();
		dao.cadastrar(categoria);
		dao.cadastrar(celular);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
