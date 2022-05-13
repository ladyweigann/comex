package br.com.alura.comex;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException{
    	
    	List<Pedido> pedidos = ProcessadorDeCsv.leitorCSV("pedidos");
    	
        RelatorioSintetico relatorioSintetico = new RelatorioSintetico(pedidos);
            
        System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", relatorioSintetico.getTotalDePedidosRealizados());
        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", relatorioSintetico.getTotalDeProdutosVendidos());
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", relatorioSintetico.getTotalDeCategorias());
        System.out.printf("- MONTANTE DE VENDAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioSintetico.montanteDeVendas.setScale(2, RoundingMode.HALF_DOWN)));
        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioSintetico.pedidoMaisBarato.getPreco().multiply(new BigDecimal(relatorioSintetico.pedidoMaisBarato.getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), relatorioSintetico.pedidoMaisBarato.getProduto());
        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioSintetico.pedidoMaisCaro.getPreco().multiply(new BigDecimal(relatorioSintetico.pedidoMaisCaro.getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), relatorioSintetico.pedidoMaisCaro.getProduto());
    }
}
