package br.com.alura.comex.dao;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;

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

    public void atualizar(Produto produto) {
        this.em.merge(produto);
    }

    public void remover(Produto produto) {
        produto = em.merge(produto);
        this.em.remove(produto);
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
}
