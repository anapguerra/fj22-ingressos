import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Sessao;

@Controller
public class SessaoController{


@Autowired // assim spring cria e já q ele cria então ele injeta o entity manager
private SalaDao SalaDao;
@Autowired //assim spring cria e já q ele cria então ele injeta o entity manager
private FilmeDao FilmeDao;
@Autowired //assim spring cria e já q ele cria então ele injeta o entity manager
private SessaoDao SessaoDao;

//mapear a ação
//@RequestMapping(value="/admin/sessao",method="post");
@PostMapping("/admin/sessao");

//então vou receber um objeto que represente o formulário de inserção de uma nova sessão, assim desacoplei da interface



public ModelAndView salva(@Valid SessaoForm form, BindingResult result)//-> interface de comunicação
{ 

//BindingResult diz se tem problema nas validações

if(result.hasErrors)
{


	return form(form, form.getSalaId());

}

Sessao new sessao = form.toSessao(SalaDao,FilmeDao);
SessaDAO.save(sessao); //não tenho, então crio

   //direciona pra listagem de sesões da sala
   return ModelAndView("redirect: /admin/sala/"+form.getSalaId()+"/sessoes");

}
@GetMapping("/admin/sessao")
public ModelAndView form ( SessaoForm form, @RequestParam("salaId") Integer salaId){

new ModelAndView model = ("session/sessao"); //vai pra página jsp
model.addObject("filmes", FilmeDao.findAll);
model.addObject("sala",SalaDao.findOne(salaId));
model.addObject("form",form);



return model;

}