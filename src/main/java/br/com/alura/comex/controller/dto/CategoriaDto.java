package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;

import java.util.List;

public class CategoriaDto {

    private Long id;
    private String nome;
    private StatusCategoria status;


    public CategoriaDto(Categoria categoria) {
        this.nome = categoria.getNome();
        this.id = categoria.getId();
        this.status = categoria.getStatus();
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public StatusCategoria getStatus() {
        return status;
    }

    public static List<CategoriaDto> converter(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaDto::new).toList();
    }
}
