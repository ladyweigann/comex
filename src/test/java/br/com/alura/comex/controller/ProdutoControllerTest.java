package br.com.alura.comex.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveriaRetornarErro500AoEnviarCategoriaIdInexistente() throws Exception {
        URI uri = new URI("/api/produtos");
        String json = "{\"nome\":\"Colecionador de Lágrimas\",\"descricao\": \"Romance Nacional\",\"precoUnitario\": 35.00,\"quantidadeEmEstoque\": 2, \"categoriaId\": 999}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(500));

    }
}