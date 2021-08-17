package br.com.alura.loja.entity.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.entity.Produto;
import br.com.alura.loja.entity.util.JPAUtil;

public class CadastroProduto {

	public static void main(String[] args) {
		
		Produto celular = new Produto();
        celular.setNome("Xiaomi Redmi");
        celular.setDescricao("Muito legal");
        celular.setPreco(new BigDecimal("800"));

        EntityManager entityManager = JPAUtil.getEntityManager();
        
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        produtoDao.cadastrar(celular);
        
        entityManager.getTransaction().begin();
        entityManager.persist(celular);
        entityManager.getTransaction().commit();
        entityManager.close();
	}
}
