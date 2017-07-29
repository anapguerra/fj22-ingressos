package br.com.caelum.ingresso.model;

import java.time.LocalTime;

import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

public class Sessao {
	@Id
	@GeneratedValue
	private Integer id;
	private LocalTime horario;
	
	@ManyToOne
	private Filme filme;
	
	@ManyToOne
	private Sala sala;
	
	/**
	 * @deprecated hibernate only
	 */
	
	public Sessao()
	{
		
	}
	
	
	public Sessao(LocalTime horario, Filme filme, Sala sala)
	{

		this.filme = filme;
		this.horario = horario;
		this.sala = sala;

	}
	
	

	

}
