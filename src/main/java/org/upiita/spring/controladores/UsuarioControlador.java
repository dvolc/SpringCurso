package org.upiita.spring.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.upiita.spring.dao.UsuarioDAO;
import org.upiita.spring.entidades.Usuario;
import org.upiita.spring.form.UsuarioForm;

/* --------------------- SOLUCIONES EJERCICIOS ------------------*/

@Controller
public class UsuarioControlador {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@RequestMapping(value = "/usuario/{usuarioId:[0-9]+}")
	public String muestraUsuario(@PathVariable Integer usuarioId, Model modelo) {

		System.out.println("mostrando /usuario/" + usuarioId);

		Usuario usuario = usuarioDAO.buscaPorId(usuarioId);
		modelo.addAttribute("usuario", usuario);

		return "usuario";
	}

	@RequestMapping(value = "/usuario/{usuarioId:[0-9]+}/editar")
	public String mostrarEditor(@RequestParam(required=false)Boolean actualizado,@PathVariable Integer usuarioId, Model modelo) {

		System.out.println("mostrando el editor para el usuario");
		
		if(modelo.containsAttribute("usuario")){
			//Si hubo errores
			System.out.println("HUbo errores, Modelo: "+ modelo);
		}
		else{
		
			Usuario usuario = usuarioDAO.buscaPorId(usuarioId);
			modelo.addAttribute("usuario", usuario);
			modelo.addAttribute("actualizado",actualizado);
		}
		return "usuario_editar";
	}

	@RequestMapping(value = "/usuario/guardar", method = RequestMethod.POST)
	public String guardarUsuario(@Valid @ModelAttribute("usuario") UsuarioForm form, BindingResult validacion, RedirectAttributes atributos) {

		String urlRedirect = null;
		System.out.println("guardando usuario, id:" + form.getId() + ", nombre:" + form.getNombre() + ", email:"
				+ form.getEmail());
		
		if(validacion.hasErrors()){
			System.out.println("VALIDACION TRUE ANADIENDO LLAVES A FLASH ATTRIBUTE");
			//ESTO ESTA DISPONIBLE DESDE LA VERSION 3.0 DE SPRING
			//ES PARA PRESERVAR DATOS EN EL REDIRECT
			atributos.addFlashAttribute("usuario", form);
			atributos.addFlashAttribute("org.springframework.validation.BindingResult.usuario", validacion);
			urlRedirect= "redirect:/blog/" + form.getId() + "/editar?actualizado=true";		
		}
		else{
		//creamos un objeto usuario con los datos que nos pasan,
		//posteriormente guardamos dicho objeto con hibernate en la tabla usuaurios
		Usuario usuario = new Usuario();
		usuario.setId(form.getId());
		usuario.setNombre(form.getNombre() );
		usuario.setEmail(form.getEmail());
		usuario.setPassword(form.getPassword());
		
		Integer idUsuarioDB = usuarioDAO.guardar(usuario);
		urlRedirect= "redirect:/usuario/" + idUsuarioDB + "/editar?actualizado=true";
		}
		//usamos el patron post-redirect-get
		return urlRedirect;

	}

}
