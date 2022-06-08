package br.com.alura.comex.mainDao;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.util.JPAUtil;
import br.com.alura.comex.vo.RelatorioProdutosMaisVendidosVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class MainProdutoDao {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto produto1 = new Produto("Senhora", "Romance Nacional", new BigDecimal("49"), 3, categoriaDao.buscarPorId(3L));
        Produto produto2 = new Produto("Colecionador de lágrimas", "Romance Nacional", new BigDecimal("49"), 3, categoriaDao.buscarPorId(3L));
        Produto produto3 = new Produto("Memórias de um Sargento de Milícias", "Romance Nacional", new BigDecimal("49"), 0, categoriaDao.buscarPorId(3L));
        Produto produto4 = new Produto("O cortiço", "Romance Nacional", new BigDecimal("49"), 0, categoriaDao.buscarPorId(3L));
        Produto produto5 = new Produto("Mouse Ergonomico", "eletronico", new BigDecimal("150"), 3, categoriaDao.buscarPorId(4L));


        produtoDao.cadastrar(produto1);
        produtoDao.cadastrar(produto2);
        produtoDao.cadastrar(produto3);
        produtoDao.cadastrar(produto4);
        produtoDao.cadastrar(produto5);

        List<Produto> todos = produtoDao.listarTodos();
        todos.forEach(p -> System.out.println(p.getNome()));

        List<Produto> indisp = produtoDao.listarIndisponiveis();
        indisp.forEach(p -> System.out.println(p.getNome()));

        List<RelatorioProdutosMaisVendidosVo> maisVendidos = produtoDao.produtosMaisVendidos();
        maisVendidos.forEach(System.out::println);

        em.getTransaction().commit();
        em.close();


    }
}
