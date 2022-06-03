package br.com.alura.comex.mainDao;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class MainCategoriaDao {

    public static void main(String[] args) {
        Categoria celulares = new Categoria("Celulares");
        Categoria informatica = new Categoria("Informatica");
        Categoria livros = new Categoria("Livros");

        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        celulares.setStatusInativa();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(informatica);
        categoriaDao.cadastrar(livros);

        List<Categoria> todos = categoriaDao.listarTodasInativas();
        todos.forEach(p -> System.out.println(p.getNome()));
        em.getTransaction().commit();
        em.close();
    }
}
