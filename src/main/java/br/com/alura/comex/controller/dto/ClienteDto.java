package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Endereco;
import org.springframework.data.domain.Page;

import java.util.List;

public class ClienteDto {

    private String nome;
    private String cpf;
    private String telefone;
    private String local;

//    private String rua;
//    private String numero;
//    private String complemento;
//    private String bairro;
//    private String cidade;
//    private String estado;

    public ClienteDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.local = cliente.getEndereco().getCidade() + "/" + cliente.getEndereco().getEstado();
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getLocal() {
        return local;
    }

    public static Page<ClienteDto> converter(Page<Cliente> clientes) {
        return clientes.map(ClienteDto::new);
    }
}
