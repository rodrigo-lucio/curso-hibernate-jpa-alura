package br.com.alura.loja.entity.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.entity.Categoria;
import br.com.alura.loja.entity.Produto;
import br.com.alura.loja.entity.util.JPAUtil;

public class CadastroProduto {

	public static void main(String[] args) {

		manipulacoesProduto(false);
				
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(9l);
		System.out.println(p.toString());
		
		List<Produto> produtos = produtoDao.buscarTodos();
		
		produtos.forEach(produto -> System.out.println(produto));
		
	}

	protected static void manipulacoesProduto(boolean limparDados) {
		Produto celular = new Produto();
		celular.setNome("Xiaomi Redmi");
		celular.setDescricao("Muito legal");
		celular.setPreco(new BigDecimal("800"));

		Produto caixaDeSom = new Produto();
		caixaDeSom.setNome("Caixa JBL");
		caixaDeSom.setDescricao("Som bem alto");
		caixaDeSom.setPreco(new BigDecimal("450"));

		
		
		Categoria categoria = new Categoria("Celulares");
		celular.setCategoria(categoria);

		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();
		categoriaDao.cadastrar(categoria);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(caixaDeSom);
		
		celular.setNome("Alterei o xiaomi");    		//Como minha entidade está persistida, esta MANAGED, gerenciada pela JPA. nesse caso vai executar um update no nome 
		
		em.flush();										// Flush, diferente do commit, ele apenas sincroniza mas não salva
		em.clear();   				 					// Limpa todas as entidades gerenciadas
		celular.setNome("Alterei o xiaomi de novo");    // Não vai executar update, pois nao esta mais gerenciada, dei o clear acima
		
		// Ciclo de vida da entidade
		// Quando da um NEW no objeto -> Transient
		// Quando da um PERSIST -> Managed (Gerenciado)
		// Commit ou Flush - Salva as alteraçoes no BD
		// Close ou Clear - Detached - Não esta mais gerenciado
		
		if(limparDados) {
			// Agora no UPDATE
			// Caso queira voltar a entidade para o estado Managed, eu chamo o merge e atribuo novamente para a entidade o valor
			// Agora se fizer o setnome vai funcionar, independente se chamar antes ou depois do merge

			celular = em.merge(celular);
			celular.setNome("Alterei o xiaomi de novo com o merge"); 
			
			em.remove(celular);
			categoria = em.merge(categoria);
			em.remove(categoria);				// O delete segue a mesma ideia do update, só é removido se estiver managed
												// Depois que é feito o close ou clear, 			
												// as entidades MANAGED passam a ser Detached, ou seja, o JPA nao esta mais gerenciando, 
												// Diferença é que o close fecha a conexao, e o clear apenas limpa as entidades gerenciadas		
		}
		
		em.getTransaction().commit();
		em.close();	
	}

	
}
