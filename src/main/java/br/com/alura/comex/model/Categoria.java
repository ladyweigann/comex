package br.com.alura.comex.model;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private StatusCategoria status = StatusCategoria.ATIVA;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria() {

    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatusCategoria getStatus() {
        return status;
    }

    public void setStatus(StatusCategoria status) {
        this.status = status;
    }

    public void setStatusInativa() {
        this.status = StatusCategoria.INATIVA;
    }
}
