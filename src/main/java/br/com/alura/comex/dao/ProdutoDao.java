package br.com.alura.comex.dao;

import br.com.alura.comex.model.Produto;
import br.com.alura.comex.vo.RelatorioPedidosPorCategoriaVo;
import br.com.alura.comex.vo.RelatorioProdutosMaisVendidosVo;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDao {
    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }


    public Produto buscarPorId(Long id) {
        return em.find(Produto.class, id);

    }
    public List<Produto> listarTodos() {
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();

    }

    public List<Produto> listarIndisponiveis() {
        String jpql = "SELECT p FROM Produto p WHERE p.quantidadeEmEstoque = 0";
        return em.createQuery(jpql, Produto.class).getResultList();

    }

    public List<RelatorioProdutosMaisVendidosVo> produtosMaisVendidos() {
        String jpql = "SELECT new br.com.alura.comex.vo.RelatorioProdutosMaisVendidosVo(produto.nome, SUM(item.quantidade)) " +
                "FROM Pedido pedido " +
                "JOIN pedido.itens item " +
                "ON SUM(item.quantidade) > 3L" +
                "JOIN item.produto produto " +
                "GROUP BY produto.nome " +
                "ORDER BY SUM(item.quantidade) DESC";
        return em.createQuery(jpql, RelatorioProdutosMaisVendidosVo.class).getResultList();
    }
}
