package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.CategoriaDto;
import br.com.alura.comex.controller.dto.CategoriaProjection;
import br.com.alura.comex.controller.form.CategoriaForm;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<CategoriaDto> lista() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return CategoriaDto.converter(categorias);
    }

    @GetMapping(path = "/pedidos")
    public List<CategoriaProjection> relatorioPedidosPorCategoria() {
        return categoriaRepository.findRelatorioPedidosCategoria();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriBuilder) {
        Categoria categoria = form.converter();
        categoriaRepository.save(categoria);

        URI uri = uriBuilder.path("/api/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }
}
