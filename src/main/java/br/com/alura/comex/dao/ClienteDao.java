package br.com.alura.comex.dao;

import br.com.alura.comex.model.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDao {
    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente cliente) {
        this.em.persist(cliente);
    }

    public void atualizar(Cliente cliente) {
        this.em.merge(cliente);
    }

    public void remover(Cliente cliente) {
        cliente = em.merge(cliente);
        this.em.remove(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return em.find(Cliente.class, id);

    }

    public List<Cliente> listarTodos() {
        String jpql = "SELECT c FROM Cliente c";
        return em.createQuery(jpql, Cliente.class).getResultList();

    }

    public List<Cliente> buscarPorNome(String nome) {
        String jpql = "SELECT c FROM Cliente c WHERE c.nome = :nome";
        return em.createQuery(jpql, Cliente.class).setParameter("nome", nome).getResultList();

    }
}
