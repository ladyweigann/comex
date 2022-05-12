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
    	
    	List<Pedido> pedidos = ProcessadorDeCsv.leitorCSV("pedidos.csv");
    	
        int totalDeProdutosVendidos = 0;
        int totalDePedidosRealizados = 0;
        BigDecimal montanteDeVendas = BigDecimal.ZERO;
        Pedido pedidoMaisBarato = null;
        Pedido pedidoMaisCaro = null;

        CategoriasProcessadas categoriasProcessadas = new CategoriasProcessadas();
        int totalDeCategorias = 0;

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedidoAtual = pedidos.get(i);

            if (pedidoAtual == null) {
                break;
            }
            
            //implementando método de verificação isMaisBaratoQue
            if (pedidoAtual.isMaisBaratoQue(pedidoMaisBarato)) {
                pedidoMaisBarato = pedidoAtual;
            }
            	
          //implementando método de verificação isMaisCaroQue
            if (pedidoAtual.isMaisCaroQue(pedidoMaisCaro)) {
                pedidoMaisCaro = pedidoAtual;
            }
            
            //substituindo pelo método criado
            montanteDeVendas = montanteDeVendas.add(pedidoAtual.getValorTotal());
            totalDeProdutosVendidos += pedidoAtual.getQuantidade();
            totalDePedidosRealizados++;

            if (!categoriasProcessadas.contains(pedidoAtual.getCategoria())) {
              totalDeCategorias++;
              categoriasProcessadas.add(pedidoAtual.getCategoria());
            }
        }

        System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", totalDePedidosRealizados);
        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", totalDeProdutosVendidos);
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", totalDeCategorias);
        System.out.printf("- MONTANTE DE VENDAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(montanteDeVendas.setScale(2, RoundingMode.HALF_DOWN)));
        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(pedidoMaisBarato.getPreco().multiply(new BigDecimal(pedidoMaisBarato.getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), pedidoMaisBarato.getProduto());
        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(pedidoMaisCaro.getPreco().multiply(new BigDecimal(pedidoMaisCaro.getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), pedidoMaisCaro.getProduto());
    }
}
