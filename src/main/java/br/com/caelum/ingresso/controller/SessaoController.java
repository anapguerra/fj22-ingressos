package br.com.caelum.ingresso.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;

@Controller
public class SessaoController{


	@Autowired // assim spring cria e já q ele cria então ele injeta o entity manager
	private SalaDao SalaDao;
	@Autowired //assim spring cria e já q ele cria então ele injeta o entity manager
	private FilmeDao FilmeDao;
	@Autowired //assim spring cria e já q ele cria então ele injeta o entity manager
	private SessaoDao SessaoDao;
	
	@GetMapping("/admin/sessao")
	public ModelAndView form ( SessaoForm form, @RequestParam("salaId") Integer salaId){
	
		ModelAndView modelAndView  = new ModelAndView("sessao/sessao"); //vai pra página jsp
		modelAndView.addObject("filmes", FilmeDao.findAll());
		modelAndView.addObject("sala",SalaDao.findOne(salaId));
		modelAndView.addObject("form",form);
		
		return modelAndView;
	
	}
	
	

	
	
	
	//mapear a ação
	@PostMapping(value = "/admin/sessao")
	@Transactional //para conseguir salvar método Dao precisa estar dentro de uma transação
	public ModelAndView salva(@Valid SessaoForm form, BindingResult result)
	//-> interface de comunicação
	{ 
	
		//BindingResult diz se tem problema nas validações
		
		if(result.hasErrors())
		{
		
			return form( form, form.getSalaId());
		
		}
		
		
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/sala/"+form.getSalaId()+"/sessoes");
		
		Sessao sessao =  form.toSessao(SalaDao,FilmeDao);
		SessaoDao.save(sessao); //não tenho, então crio
		
	   //direciona pra listagem de sesões da sala
	   return modelAndView;
	
	}
}
