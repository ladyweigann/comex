package br.com.alura.comex.repository;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long>, JpaRepository<Produto, Long> {
    List<Produto> findAllByOrderByNomeAsc(Pageable pageable);
}