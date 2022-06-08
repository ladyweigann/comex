package br.com.alura.comex.mainDao;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class MainCategoriaDao {

    public static void main(String[] args) {
        Categoria categoria1 = new Categoria("Celulares");
        Categoria categoria2 = new Categoria("Informatica");
        Categoria categoria3 = new Categoria("Livros");

        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoria1.setStatusInativa();

        categoriaDao.cadastrar(categoria1);
        categoriaDao.cadastrar(categoria2);
        categoriaDao.cadastrar(categoria3);

        categoria2.setNome("Casa");
        Categoria categoria4 = new Categoria("Informatica");
        categoriaDao.atualizar(categoria2);
        categoriaDao.cadastrar(categoria4);

        List<Categoria> todos = categoriaDao.listarTodas();
        todos.forEach(p -> System.out.println(p.getNome()));

        em.getTransaction().commit();
        em.close();
    }
}
