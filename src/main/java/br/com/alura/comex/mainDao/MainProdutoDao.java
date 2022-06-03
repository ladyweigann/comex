package br.com.alura.comex.mainDao;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class MainProdutoDao {

    public static void main(String[] args) {

        Categoria livros = new Categoria("Livros");
        Produto produto1 = new Produto("Senhora", "Romance Nacional", new BigDecimal("49"), 3, livros);
        Produto produto2 = new Produto("Colecionador de lágrimas", "Romance Nacional", new BigDecimal("49"), 3, livros);
        Produto produto3 = new Produto("Memórias de um Sargento de Milícias", "Romance Nacional", new BigDecimal("49"), 0, livros);
        Produto produto4 = new Produto("O cortiço", "Romance Nacional", new BigDecimal("49"), 0, livros);

        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);


        em.getTransaction().begin();

        categoriaDao.cadastrar(livros);
        produtoDao.cadastrar(produto1);
        produtoDao.cadastrar(produto2);
        produtoDao.cadastrar(produto3);
        produtoDao.cadastrar(produto4);

        List<Produto> todos = produtoDao.listarTodos();
        todos.forEach(p -> System.out.println(p.getNome()));

        List<Produto> indisp = produtoDao.listarIndisponiveis();
        indisp.forEach(p -> System.out.println(p.getNome()));
        
        em.getTransaction().commit();
        em.close();
    }
}
