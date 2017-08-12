package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Ingresso {
	
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Sessao sessao;
	private BigDecimal preco;
	private Lugar lugar;
	
	/*
	 * deprecated hibernate only
	 */

	
	public Ingresso (Sessao sessao, TipoDeIngresso tipoDeDesconto, Lugar lugar){
		this.sessao = sessao;
		this.preco = tipoDeDesconto.aplicarDescontoSobre(sessao.getPreco());
		this.lugar = lugar;
		
	}
	
	public Sessao getSessao(){
		return sessao;
	}
	
	public BigDecimal getPreco(){
		return preco;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	
	
	
}
