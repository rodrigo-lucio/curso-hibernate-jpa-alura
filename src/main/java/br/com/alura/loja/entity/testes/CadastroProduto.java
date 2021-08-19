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

		EntityManager em = JPAUtil.getEntityManager();

		Dao<Object> dao = new Dao<Object>(em);

		em.getTransaction().begin();
		dao.cadastrar(categoria);
		dao.cadastrar(celular);
		
		celular.setNome("Alterei o xiaomi");    		//Como minha entidade está persistida, esta MANAGED, gerenciada pela JPA. nesse caso vai executar um update no nome 
		
		em.flush();										// Flush, diferente do commit, ele apenas sincroniza mas não salva
		em.clear();   				 		
		celular.setNome("Alterei o xiaomi de novo");    //Não vai executar update
		
		// Ciclo de vida da entidade
		// Quando da um NEW no objeto -> Transient
		// Quando da um PERSIST -> Managed (Gerenciado)
		// Commit ou Flush - Salva as alteraçoes no BD
		// Close ou Clear - Detached - Não esta mais gerenciado
		
		
		// Agora no UPDATE
		// Caso queira voltar a entidade para o estado Managed, eu chamo o merge e atribuo novamente para a entidade o valor
		// Agora se fizer o setnome vai funcionar, independente se chamar antes ou depois do merge

		celular = em.merge(celular);
		celular.setNome("Alterei o xiaomi de novo com o merge"); 
		em.getTransaction().commit();
		em.close();							// Depois que é feito o close ou clear, 
											// as entidades MANAGED passam a ser Detached, ou seja, o JPA nao esta mais gerenciando, 
											// Diferença é que o close fecha a conexao, e o clear apenas limpa as entidades gerenciadas
			
		
		
	}
}
