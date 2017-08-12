package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

public class DescontoDeTrintaPorCentoParaBancos implements Desconto{
	
	private BigDecimal fator = new BigDecimal("0.7");
	
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal.multiply(fator);
	}
	
	public String getDescricao()
	{
		return "30% p/ bancos";
	}

}
