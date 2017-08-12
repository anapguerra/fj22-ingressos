package br.com.caelum.ingresso.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class Carrinho {
	
	private Set<Ingresso> ingressos = new HashSet<Ingresso>();
	
	public void add(Ingresso ingresso){
		ingressos.add(ingresso);
	}

}
