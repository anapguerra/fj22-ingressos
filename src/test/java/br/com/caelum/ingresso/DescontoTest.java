package br.com.caelum.ingresso;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.DescontoEstudante;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.SemDesconto;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;


public class DescontoTest {

	
	@Test
	public void deveConcederDescontoDe30PorCentoParaIngressosDeClientesDeBancos(){
		
		Lugar lugar = new Lugar("A",1);
		Sala sala = new Sala("Sala para teste", new BigDecimal("20.5"));
		Filme filme = new Filme("Alice no País das Maravilhas", Duration.ofMinutes(120), 
				"Aventura", new BigDecimal("12.0"));
		
		Sessao sessao = new Sessao(LocalTime.now(),filme, sala);
		
		Ingresso ingresso = new Ingresso(sessao,  TipoDeIngresso.BANCO,lugar);
		
		BigDecimal precoEsperado = new BigDecimal("22.75");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
		
		
		
	}
	
	@Test
	public void deveConcederDescontoDe50PorCentoParaIngressosDeEstudantes(){
		
		Lugar lugar = new Lugar("A",1);
		Sala sala = new Sala("Sala para teste", new BigDecimal("20.5"));
		Filme filme = new Filme("Alice no País das Maravilhas", Duration.ofMinutes(120), 
				"Aventura", new BigDecimal("12.0"));
		
		Sessao sessao = new Sessao(LocalTime.now(),filme, sala);
		
		Ingresso ingresso = new Ingresso(sessao,  TipoDeIngresso.ESTUDANTE,lugar);
		
		BigDecimal precoEsperado = new BigDecimal("16.25");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
		
		
		
	}
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal(){
		
		Lugar lugar = new Lugar("A",1);
		Sala sala = new Sala("Sala para teste", new BigDecimal("20.5"));
		Filme filme = new Filme("Alice no País das Maravilhas", Duration.ofMinutes(120), 
				"Aventura", new BigDecimal("12.0"));
		
		Sessao sessao = new Sessao(LocalTime.now(),filme, sala);
		
		Ingresso ingresso = new Ingresso(sessao,  TipoDeIngresso.INTEIRO,lugar);
		
		BigDecimal precoEsperado = new BigDecimal("32.5");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
		
		
		
	}

	
}
