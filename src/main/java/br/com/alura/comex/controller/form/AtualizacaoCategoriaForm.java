package br.com.alura.comex.controller.form;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AtualizacaoCategoriaForm {

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria atualizar(Long id, CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.getOne(id);

        categoria.setNome(this.nome);

        return categoria;
    }
}
