package br.com.alura.comex.mainDao;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.dao.PedidoDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.model.*;
import br.com.alura.comex.util.JPAUtil;
import br.com.alura.comex.vo.RelatorioPedidosPorCliente;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class MainPedidoDao {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();
        PedidoDao pedidoDao = new PedidoDao(em);
        em.getTransaction().commit();

        List<RelatorioPedidosPorCliente> relatorio = pedidoDao.pedidosPorCliente();
        relatorio.forEach(System.out::println);

    }

}
