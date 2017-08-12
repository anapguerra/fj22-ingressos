package br.com.caelum.ingresso.controller;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.ImagemDaCapa;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.rest.ImdbClient;

@Controller
public class SessaoController{


	@Autowired // assim spring cria e já q ele cria então ele injeta o entity manager
	private SalaDao SalaDao;
	@Autowired //assim spring cria e já q ele cria então ele injeta o entity manager
	private FilmeDao FilmeDao;
	@Autowired //assim spring cria e já q ele cria então ele injeta o entity manager
	private SessaoDao SessaoDao;
	
	
	
	@Autowired
    private ImdbClient client;
	
	@Autowired
	private Carrinho carrinho;
	
	
	@GetMapping("/sessao/{id}/lugares")
	public ModelAndView lugaresNaSessao(@PathVariable("id") Integer id){
		
		ModelAndView modelAndView  = new ModelAndView("sessao/lugares");//vai pra página jsp
		
		Sessao sessao =  SessaoDao.findOne(id);
		
		Optional<ImagemDaCapa> capa = client.request(sessao.getFilme(),ImagemDaCapa.class);
		
		modelAndView.addObject("imagemDaCapa", capa.orElse(new ImagemDaCapa()));
		modelAndView.addObject("sessao", sessao);
		modelAndView.addObject("carrinho",carrinho);
		modelAndView.addObject("tiposDeIngressos",TipoDeIngresso.values());
		
		return modelAndView;
	}
	
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
