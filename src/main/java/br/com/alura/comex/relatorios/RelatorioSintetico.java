package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

import br.com.alura.comex.FormatarSaidaDeValores;
import br.com.alura.comex.Pedido;

public class RelatorioSintetico {

	private int totalDeProdutosVendidos;
	private int totalDePedidosRealizados;
	private BigDecimal montanteDeVendas = BigDecimal.ZERO;
	private Pedido pedidoMaisBarato;
	private Pedido pedidoMaisCaro;
	private long categoriasProcessadas;
	FormatarSaidaDeValores formatar = new FormatarSaidaDeValores();
	
	public RelatorioSintetico(List<Pedido> pedidos) {
		
		
		if(pedidos == null || pedidos.isEmpty()) {
			throw new IllegalArgumentException("A lista não pode estar vazia");
		}
		
		this.montanteDeVendas = getMontanteDeVendas(pedidos);
		this.totalDeProdutosVendidos = getTotalDeProdutosVendidos(pedidos);
		this.totalDePedidosRealizados = getTotalDePedidosRealizados(pedidos);
		this.categoriasProcessadas = getCategoriasProcessadas(pedidos);
		this.pedidoMaisBarato = getPedidoMaisBarato(pedidos);
		this.pedidoMaisCaro = getPedidoMaisCaro(pedidos);

	}
	
	public int getTotalDeProdutosVendidos(List<Pedido> pedidos) {
		return pedidos.stream().mapToInt(Pedido::getQuantidade).sum();
	}

	public int getTotalDePedidosRealizados(List<Pedido> pedidos) {
		return pedidos.size();
	}

	public BigDecimal getMontanteDeVendas(List<Pedido> pedidos) {
		return pedidos.stream().map(Pedido::getValorTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_DOWN);
	}

	public long getCategoriasProcessadas(List<Pedido> pedidos) {
		return pedidos.stream().map(Pedido::getCategoria).distinct().count();
	}

	public Pedido getPedidoMaisBarato(List<Pedido> pedidos) {
		return pedidos.stream()
				.min(Comparator.comparing(Pedido::getValorTotal))
				.orElseThrow(() -> new IllegalStateException("A lista não pode estar vazia"));
	}
	public Pedido getPedidoMaisCaro(List<Pedido> pedidos) {
		return pedidos.stream()
				.max(Comparator.comparing(Pedido::getValorTotal))
				.orElseThrow(() -> new IllegalStateException("A lista não pode estar vazia"));
	}

	
	@Override
    public String toString() {
		
        return "#### RELATÓRIO DE VALORES TOTAIS" +
                "\n- TOTAL DE PEDIDOS REALIZADOS: " + totalDePedidosRealizados +
                "\n- TOTAL DE PRODUTOS VENDIDOS: " + totalDeProdutosVendidos +
                "\n- TOTAL DE CATEGORIAS: " + categoriasProcessadas +
                "\n- MONTANTE DE VENDAS: " + formatar.formatarSaidaDeValores(montanteDeVendas) +
                "\n- PEDIDO MAIS BARATO: " + formatar.formatarSaidaDeValores(pedidoMaisBarato.getValorTotal()) + " (" + pedidoMaisBarato.getProduto() + ")" +
                "\n- PEDIDO MAIS CARO: " + formatar.formatarSaidaDeValores(pedidoMaisCaro.getValorTotal()) + " (" + pedidoMaisCaro.getProduto() + ")";
    }

	
}
