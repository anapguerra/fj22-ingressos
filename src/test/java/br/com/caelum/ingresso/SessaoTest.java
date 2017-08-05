package br.com.caelum.ingresso;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class SessaoTest {
	
	
	@Test
	public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFilme(){
		
		Sala sala = new Sala("Sala para teste", new  BigDecimal("12.5"));
		Filme filme = new Filme("Alice no País das Maravilhas", Duration.ofMinutes(120), "Aventura", new BigDecimal("12.0"));
		
		BigDecimal somaDosPrecosDaSalaEFilme = sala.getPreco().add(filme.getPreco()); 
		
		Sessao sessao = new Sessao(LocalTime.now(),filme, sala);
		
		Assert.assertEquals(somaDosPrecosDaSalaEFilme, sessao.getPreco());
		
	}

}
