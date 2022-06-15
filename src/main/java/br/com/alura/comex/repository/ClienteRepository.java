package br.com.alura.comex.repository;

import br.com.alura.comex.model.Cliente;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, PagingAndSortingRepository<Cliente, Long> {
    List<Cliente> findAllByOrderByNomeAsc(Pageable pageable);
}