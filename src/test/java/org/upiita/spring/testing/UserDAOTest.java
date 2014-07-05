package org.upiita.spring.testing;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.dao.UsuarioDAO;
import org.upiita.spring.entidades.DatosUsuario;
import org.upiita.spring.entidades.Departamento;
import org.upiita.spring.entidades.Usuario;

import static org.springframework.util.Assert.*;


public class UserDAOTest {
	
	private static ApplicationContext contexto;
	
	private static UsuarioDAO usuarioDAO;
	
	@BeforeClass
	public static void inicializar() {
		
		contexto = new ClassPathXmlApplicationContext("dao-context-testing.xml");
		usuarioDAO = (UsuarioDAO) contexto.getBean("usuarioDAO");
	}
	
	
	@Test
	public void buscaUsuarioTest(){
		
		Usuario usr = usuarioDAO.buscaPorId(1);
		System.out.println("Nombre del Usuario: "+usr.getNombre());
		System.out.println("POST del usuario: "+usr.getPost().size());
		Assert.assertNotNull("El metodo para buscar post regresa datos vacios",usr);	
	}
	
	
	@Test
	public void guardarUsuarioTest(){
		
		Usuario usr = new Usuario();
//		usr.setId(10);
		usr.setNombre("Daniel");
		usr.setPassword("new");
		usr.setEmail("maiol");
		
		Integer idUsr = usuarioDAO.guardar(usr);
		Usuario usuarioBD = usuarioDAO.buscaPorId(idUsr);

		Assert.assertNotNull("El metodo para buscar Usuario regresa datos vacios",usuarioBD);
		
		Assert.assertNotNull(usuarioBD.getId());
		Assert.assertEquals(usr.getId(), usuarioBD.getId());
		
		Assert.assertNotNull(usuarioBD.getNombre());
		Assert.assertEquals(usr.getNombre(), usuarioBD.getNombre());
		
		Assert.assertNotNull(usr.getPassword());
		Assert.assertEquals(usr.getPassword(),usuarioBD.getPassword());
		
		Assert.assertNotNull(usr.getEmail());
		Assert.assertEquals(usr.getEmail(),usuarioBD.getEmail());
		
		
	}
	
	
	/**
	 * Metodo para validar mediante Criteria y Restrictions 
	 * si usr y pss son iguales
	 */
	@Test
	public void buscaUsuarioLoginTest(){

		Usuario usr = usuarioDAO.login("juan", "1234");
		System.out.println("Equivalencia correcta entre USUARIO: "+ usr.getNombre() + " y pass: " +usr.getPassword());
		Assert.assertNotNull(usr.getId());
		 
	}
	
	
	@Test
	public void mapeoUsuarioDatosUsuarioTest(){

		Usuario usr = usuarioDAO.buscaPorId(1);
		System.out.println("usuario nombre: "+ usr.getNombre());
		System.out.println("datos usuario : "+ usr.getDatosUsuarios().getBiografia());
		Assert.assertNotNull(usr.getId()); 
		
		DatosUsuario datosUsuario = usr.getDatosUsuarios();
		System.out.println("nombre usuario desde Datos Usuario: "+ datosUsuario.getUsuario().getNombre());
	}
	
	@Test
	public void mapeoUsuarioDepartamentoTest(){

		Usuario usr = usuarioDAO.buscaPorId(1);
		System.out.println("usuario nombre: "+ usr.getNombre());
		System.out.println("Departamentos : "+ usr.getDepartammento());
		
		Assert.assertNotNull(usr.getId()); 
		notEmpty(usr.getDepartammento());
		
		List<Departamento> listDepartamentos = usr.getDepartammento();
		
		for (Departamento departamento : listDepartamentos) {
			System.out.println("nombre usuario desde Departamentos : "+ departamento.getNombre());
		}
		
		
	}


}
