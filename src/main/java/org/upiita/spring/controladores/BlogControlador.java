package org.upiita.spring.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.upiita.spring.dao.PostDAO;
import org.upiita.spring.entidades.Post;
import org.upiita.spring.form.FormaSession;
import org.upiita.spring.form.PostForm;


//ESTO ES ANNOTATION Y LE DICE A SPRING
//QUE ESTA CLASE ES UN CONTROLADOR
@Controller
public class BlogControlador {
	
	@Autowired
	private FormaSession formaSession;

	//Esta anotacion va a agarrar del contexto de Spring la Clace Hibernate
	//y va a inyectar la dependencia en esta clace
	//Qualifire hace solo se inyecte una dependencia en particular 
	//esta se declaro en el DAO HibernatePostDAO con
	// Component(value="postDAO")
	// Se utilizan las Interfaces para mejorar el acoplamiento y facilitar la reutilizacion de codigo
	@Autowired
	@Qualifier(value="postDAO")
	private PostDAO postDAO;
	
	//EXPRESION REGULAR:
	//   []  = DEFINIR RANGOS
	//    + = AL MENOS UN ELEMENTO DEL ANTERIOR EXPRESION
	@RequestMapping(value="/blog/{postId:[0-9]+}")
	public String mostrarInicio(@PathVariable(value="postId") Integer postId, @RequestParam(required=false) Integer limite, @RequestParam(required=false) String nombre, Model modelo){
		
		System.out.println("limite:" + limite);
		System.out.println("despachando url /blog/" + postId);
		
		
		
		Post elPost = postDAO.buscaPorId(postId);
		
		modelo.addAttribute("post", elPost);
		modelo.addAttribute("nombre",nombre);
				
		System.out.println("ITEMS COMPRADOS: "+ formaSession.getItemsComprados());
		
		return "post";
	}
	
	//POR DEFAULT, LAS URLS SON DEL TIPO GET
	@RequestMapping(value="/blog/{postId:[0-9]+}/editar")
	public String mostrarEditor(@RequestParam(required=false) Boolean actualizado, @PathVariable Integer postId, Model modelo){
		
		if(modelo.containsAttribute("post")){
			//Si hubo errores
			System.out.println("HUbo errores, Modelo: "+ modelo);
		}
		else{
			Post post = postDAO.buscaPorId(postId);
			modelo.addAttribute("post",post);
			modelo.addAttribute("actualizado",actualizado);
		}
		
		System.out.println("ITEMS COMPRADOS: "+ formaSession.getItemsComprados());
		return "post_editar";
	}
	
//	@RequestMapping(value="/blog/guardar",method=RequestMethod.POST)
//	public String guardarPost(Integer id, @RequestParam(value="titulo") String title, String contenido, Integer numero){
//		
//		System.out.println("id:" + id + "titulo:" + title + ", contenido:" + contenido + ", numero:" + numero);
//		//@TODO: MAS ADELANTE GUARDAMOS ESTOS EN LA BASE DE DATOS
//		
//		Post post = new Post();
//		post.setId(id);
//		post.setTitulo(title);
//		post.setContenido(contenido);
//		
//		Integer postIdGuardado = postDAO.guardar(post);
//		
//		
//		//@TODO: MAS ADELANTE CAMBIERAMOS ESTO POR EL PATRON
//		// POST - REDIRECT - GET
//		return "redirect:/blog/" + postIdGuardado + "/editar?actualizado=true";		
//	}
	
	//Debe ir el BindingResult al final de los parametros
	//RedirectAttributes es una clace de Spring que permite conservar los atributos en un redirect si que se pierdan
	@RequestMapping(value="/blog/guardar",method=RequestMethod.POST)
	public String guardarPost(@Valid @ModelAttribute("post") PostForm forma, BindingResult validacion, RedirectAttributes atributos){
		
		String urlRedirect = null;
		System.out.println("id:" + forma.getId() + "titulo:" + forma.getTitulo() + ", contenido:" + forma.getContenido()) ;
		//@TODO: MAS ADELANTE GUARDAMOS ESTOS EN LA BASE DE DATOS
		
		if(validacion.hasErrors()){
			//ESTO ESTA DISPONIBLE DESDE LA VERSION 3.0 DE SPRING
			//ES PARA PRESERVAR DATOS EN EL REDIRECT
			atributos.addFlashAttribute("post", forma);
			atributos.addFlashAttribute("org.springframework.validation.BindingResult.post", validacion);
			urlRedirect= "redirect:/blog/" + forma.getId() + "/editar?actualizado=true";		
		}
		else{
			
			Post post = new Post();
			post.setId(forma.getId());
			post.setTitulo( forma.getTitulo());
			post.setContenido( forma.getContenido());
			
			Integer postIdGuardado = postDAO.guardar(post);
			//@TODO: MAS ADELANTE CAMBIERAMOS ESTO POR EL PATRON
			// POST - REDIRECT - GET
			urlRedirect= "redirect:/blog/" + postIdGuardado + "/editar?actualizado=true";		
		}
		return urlRedirect;
	}
	

}








