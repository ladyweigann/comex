package br.com.alura.comex.dao;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.vo.RelatorioClientesMaisLucrativosVo;
import br.com.alura.comex.vo.RelatorioPedidosPorCategoriaVo;
import br.com.alura.comex.vo.RelatorioPedidosPorClienteVo;

import javax.persistence.EntityManager;
import java.util.List;

public class PedidoDao {

    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        this.em.persist(pedido);
    }
    public Pedido buscarPorId(Long id) {
        return em.find(Pedido.class, id);
    }

    public List<RelatorioPedidosPorClienteVo> pedidosPorCliente() {
        String jpql = "SELECT new br.com.alura.comex.vo.RelatorioPedidosPorClienteVo(pedido.cliente.nome, COUNT(pedido.cliente))" +
                "FROM Pedido pedido " +
                "GROUP BY pedido.cliente.nome ";
        return em.createQuery(jpql, RelatorioPedidosPorClienteVo.class).getResultList();
    }

    public List<RelatorioPedidosPorCategoriaVo> pedidosPorCategoria() {
        String jpql = "SELECT new br.com.alura.comex.vo.RelatorioPedidosPorCategoriaVo(produto.categoria.nome, SUM(item.quantidade))" +
                "FROM Pedido pedido " +
                "JOIN pedido.itens item " +
                "JOIN item.produto produto " +
                "GROUP BY produto.categoria.nome ";
        return em.createQuery(jpql, RelatorioPedidosPorCategoriaVo.class).getResultList();
    }

    public List<RelatorioClientesMaisLucrativosVo> clientesMaisLucrativos() {
        String jpql = "SELECT new br.com.alura.comex.vo.RelatorioClientesMaisLucrativosVo(pedido.cliente.nome, SUM(pedido.valorTotal))" +
                "FROM Pedido pedido " +
                "GROUP BY pedido.cliente.nome " +
                "ORDER BY SUM(pedido.valorTotal) DESC";
        return em.createQuery(jpql, RelatorioClientesMaisLucrativosVo.class).getResultList();
    }

}
