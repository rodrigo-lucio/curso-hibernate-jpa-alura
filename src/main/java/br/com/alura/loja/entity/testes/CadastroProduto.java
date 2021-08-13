package br.com.alura.loja.entity.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.entity.Produto;

public class CadastroProduto {

	public static void main(String[] args) {
		
		Produto celular = new Produto();
        celular.setNome("Xiaomi Redmi");
        celular.setDescricao("Muito legal");
        celular.setPreco(new BigDecimal("800"));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja"); //name la da tag persistence-unit do persistence.xml
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(celular);
        entityManager.getTransaction().commit();
        entityManager.close();
	}
}
