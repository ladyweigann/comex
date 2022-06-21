package br.com.alura.comex.repository;

import br.com.alura.comex.controller.dto.CategoriaProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository repository;

    @Test
    public void deveriaCarregarRelatorioPedidosPorCategoria() {
        String nome = "Livros";
        String nome2 = "Informática";
        BigDecimal montanteVendido = new BigDecimal("49.00");
        BigDecimal montanteVendido2 = new BigDecimal("477.00");

        List<CategoriaProjection> relatorioPedidosCategoria = repository.findRelatorioPedidosCategoria();

        Assertions.assertNotNull(relatorioPedidosCategoria);
        Assertions.assertEquals(2, relatorioPedidosCategoria.size());
        Assertions.assertEquals(montanteVendido, relatorioPedidosCategoria.get(0).getMontanteVendido());
        Assertions.assertEquals(montanteVendido2, relatorioPedidosCategoria.get(1).getMontanteVendido());
        Assertions.assertEquals(nome, relatorioPedidosCategoria.get(0).getNome());
        Assertions.assertEquals(nome2, relatorioPedidosCategoria.get(1).getNome());

    }

}