package br.com.alura.comex;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatarSaidaDeValores {

	public String formatarSaidaDeValores(BigDecimal valor) {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor);
	}

}
