package br.com.alura.comex.mainDao;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.dao.PedidoDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.util.JPAUtil;
import br.com.alura.comex.vo.RelatorioClientesMaisLucrativosVo;
import br.com.alura.comex.vo.RelatorioPedidosPorCategoriaVo;
import br.com.alura.comex.vo.RelatorioPedidosPorClienteVo;

import javax.persistence.EntityManager;
import java.util.List;

public class MainPedidoDao {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();
        PedidoDao pedidoDao = new PedidoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);

        Pedido pedido = new Pedido(clienteDao.buscarPorId(1L));
        pedido.adicionarItem(new ItemDePedido(1, produtoDao.buscarPorId(1L), pedido));
        pedido.adicionarItem(new ItemDePedido(1, produtoDao.buscarPorId(2L), pedido));
        pedido.adicionarItem(new ItemDePedido(2, produtoDao.buscarPorId(5L), pedido));

        Pedido pedido2 = new Pedido(clienteDao.buscarPorId(2L));
        pedido2.adicionarItem(new ItemDePedido(4, produtoDao.buscarPorId(1L), pedido2));
        pedido2.adicionarItem(new ItemDePedido(3, produtoDao.buscarPorId(2L), pedido2));
        pedido2.adicionarItem(new ItemDePedido(2, produtoDao.buscarPorId(5L), pedido2));

        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);

        em.getTransaction().commit();

        List<RelatorioPedidosPorClienteVo> relatorio = pedidoDao.pedidosPorCliente();
        relatorio.forEach(System.out::println);

        System.out.println();
        List<RelatorioPedidosPorCategoriaVo> relatorioCategoria = pedidoDao.pedidosPorCategoria();
        relatorioCategoria.forEach(System.out::println);

        List<RelatorioClientesMaisLucrativosVo> relatorioClientesMaisLucrativos = pedidoDao.clientesMaisLucrativos();
        relatorioClientesMaisLucrativos.forEach(System.out::println);
        em.close();

    }

}
