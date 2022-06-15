package br.com.alura.comex.controller.dto;

import java.math.BigDecimal;

public interface CategoriaProjection {

    String getNome();
    Long getQuantidade();
    BigDecimal getMontanteVendido();

}
