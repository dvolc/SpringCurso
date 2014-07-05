package org.upiita.spring.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.upiita.spring.form.FormaSession;

@Controller
public class LoginControlador {
	
	@Autowired
	private FormaSession formaSession;
	
	@RequestMapping(value="/login")
	public String login(Boolean errorLogin, Model modelo, HttpSession sesion){
		
		System.out.println("LOGIn ENTRANDO");
		sesion.setAttribute("variable", 30);
		
		modelo.addAttribute("errorLogin", errorLogin);
		formaSession.setItemsComprados(100);
		return "login";
	}
	
	@RequestMapping(value="/error-403")
	public String error403(Authentication autenticacion, Model model){
		System.out.println("LOGIn Error 403");
		System.out.println("Nombre: "+autenticacion.getName());
		System.out.println("Password: "+autenticacion.getCredentials());
		
		System.out.println("Roles: "+autenticacion.getAuthorities());
		
		model.addAttribute("email",autenticacion.getName() );
		
		return "error403";
	}

}
