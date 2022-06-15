package br.com.alura.comex.repository;

import br.com.alura.comex.controller.dto.CategoriaProjection;
import br.com.alura.comex.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Categoria findByNome(String nome);

    @Query(value = "SELECT categoria.nome AS nome, sum(item_pedido.quantidade) AS quantidade, sum((item_pedido.quantidade * item_pedido.preco_unitario)) AS montanteVendido \n" +
            "FROM categoria \n" +
            "INNER JOIN produto ON produto.categoria_id = categoria.id \n" +
            "INNER JOIN item_pedido ON item_pedido.produto_id = produto.id \n" +
            "INNER JOIN pedido ON pedido.id = item_pedido.pedido_id\n" +
            "GROUP BY categoria.nome;", nativeQuery = true)
    List<CategoriaProjection> findRelatorioPedidosCategoria();
}