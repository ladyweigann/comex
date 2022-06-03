package br.com.alura.comex.dao;

import br.com.alura.comex.model.Categoria;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaDao {
    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria) {
        this.em.persist(categoria);
    }

    public void atualizar(Categoria categoria) {
        this.em.merge(categoria);
    }

    public Categoria buscarPorId(Long id) {
        return em.find(Categoria.class, id);

    }
    public List<Categoria> listarTodas() {
        String jpql = "SELECT c FROM Categoria c";
        return em.createQuery(jpql, Categoria.class).getResultList();

    }

    public List<Categoria> listarTodasInativas() {
        String jpql = "SELECT c FROM Categoria c WHERE c.status = 'INATIVA'";
        return em.createQuery(jpql, Categoria.class).getResultList();

    }
}
