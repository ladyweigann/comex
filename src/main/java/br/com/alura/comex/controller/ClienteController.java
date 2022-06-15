package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.ClienteDto;
import br.com.alura.comex.controller.form.ClienteForm;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<ClienteDto> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ClienteDto.converter(clientes);
    }
    @GetMapping("/pag/{numeroPagina}")
    public List<ClienteDto> listaPaginada(@PathVariable int numeroPagina) {
        Pageable pageable = PageRequest.of((numeroPagina-1), 5);
        List<Cliente> clientes = clienteRepository.findAllByOrderByNomeAsc(pageable);
        return ClienteDto.converter(clientes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(new ClienteDto(cliente.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
        Cliente cliente = form.converter();
        clienteRepository.save(cliente);

        URI uri = uriBuilder.path("/api/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDto(cliente));
    }

//    @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity<ClienteDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoClienteForm form) {
//        Optional<Categoria> optional = categoriaRepository.findById(id);
//        if (optional.isPresent()) {
//            Categoria categoria = form.atualizar(id, categoriaRepository);
//            return ResponseEntity.ok(new CategoriaDto(categoria));
//        }
//
//        return ResponseEntity.notFound().build();
//    }
}
