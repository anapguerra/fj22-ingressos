package br.com.caelum.ingresso.model.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class SessaoForm {

	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	@NotNull
	private Integer SalaId;
	@NotNull
	private Integer FilmeId;
	@NotNull /*checar formato*/
	@DateTimeFormat(pattern="HH:mm")
	private LocalTime horario;

	//getters e setters

	public Integer getSalaId() {
		return SalaId;
	}

	public void setSalaId(Integer salaId) {
		SalaId = salaId;
	}

	public Integer getFilmeId() {
		return FilmeId;
	}

	public void setFilmeId(Integer filmeId) {
		FilmeId = filmeId;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	

	public Sessao toSessao(SalaDao salaDao, FilmeDao filmeDao){

	       Sala sala = salaDao.findOne(SalaId); //assim o Spring deixa
	       Filme filme = filmeDao.findOne(FilmeId); //assim o Spring deixa

	       Sessao sessao = new Sessao(this.horario, filme, sala);
	       sessao.setId(id);

	       return sessao;

	}

	

	
	
}
