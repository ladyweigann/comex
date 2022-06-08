package br.com.alura.comex.mainDao;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.dao.PedidoDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.util.JPAUtil;
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
        pedido.adicionarItem(new ItemDePedido(1, produtoDao.buscarPorId(1L), pedidoDao.buscarPorId(1L)));
        pedido.adicionarItem(new ItemDePedido(1, produtoDao.buscarPorId(2L), pedidoDao.buscarPorId(2L)));
        pedido.adicionarItem(new ItemDePedido(2, produtoDao.buscarPorId(5L), pedidoDao.buscarPorId(2L)));

        pedidoDao.cadastrar(pedido);

        em.getTransaction().commit();

        List<RelatorioPedidosPorClienteVo> relatorio = pedidoDao.pedidosPorCliente();
        relatorio.forEach(System.out::println);
        System.out.println();
        List<RelatorioPedidosPorCategoriaVo> relatorioCategoria = pedidoDao.pedidosPorCategoria();
        relatorioCategoria.forEach(System.out::println);

        em.close();

    }

}
