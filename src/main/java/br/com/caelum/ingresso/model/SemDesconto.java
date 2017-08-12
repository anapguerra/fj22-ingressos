package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

public class SemDesconto implements Desconto{
	
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal;
	}

	public String getDescricao()
	{
		return "Normal";
	}
}
