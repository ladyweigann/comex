package br.com.alura.comex.mainDao;


import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class MainClienteDao {

    public static void main(String[] args) {

        Cliente cliente1 = new Cliente("Lari", "11122233345", "8399999888", "Rua Oito", "92", "C", "Portal", "CG", "PB");
        Cliente cliente2 = new Cliente("Pedro", "22233388899", "99999999", "Rua Dez", "33", "C", "Portal", "CG", "PB");
        Cliente cliente3 = new Cliente("João", "54698745632", "99999999", "Rua Treze", "12", "A", "Portal", "CG", "PB");
        EntityManager em = JPAUtil.getEntityManager();

        ClienteDao clienteDao = new ClienteDao(em);


        em.getTransaction().begin();


        clienteDao.cadastrar(cliente1);
        clienteDao.cadastrar(cliente2);
        clienteDao.cadastrar(cliente3);

        List<Cliente> buscaPorNome = clienteDao.buscarPorNome("Lari");
        buscaPorNome.forEach(p -> System.out.println(p.getNome()));


        em.getTransaction().commit();
        em.close();
    }
}
