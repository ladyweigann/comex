package br.com.alura.comex.dao;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.vo.RelatorioPedidosPorCliente;

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

    public List<RelatorioPedidosPorCliente> pedidosPorCliente() {
        String jpql = "SELECT new br.com.alura.comex.vo.RelatorioPedidosPorCliente(pedido.cliente.nome, COUNT(pedido.cliente))" +
                "FROM Pedido pedido " +
                "GROUP BY pedido.cliente.nome ";
        return em.createQuery(jpql, RelatorioPedidosPorCliente.class).getResultList();
    }

}
