package org.upiita.spring.testing;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// una manera de combinar metodos del mismo nombre pero de claces diferentes
//diponible desde la version 1.5
import static org.springframework.util.Assert.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.dao.PostDAO;
import org.upiita.spring.entidades.Post;

public class PostDAOTest {
	
	//HAY 2 TIPOS DE PRUEBAS:
	//  PRUEBA UNITARIA (	
	//  PRUEBA DE INTEGRACION (BD o CHECAR QUE LA APLICACION WEB FUNCIONA)

	private static ApplicationContext contexto;
	
	private static PostDAO postDAO;
	
	

	@BeforeClass
	public static void inicializar() {
		
		contexto = new ClassPathXmlApplicationContext("dao-context-testing.xml");
		postDAO = (PostDAO) contexto.getBean("postDAO");
	}
	
	@Test
	public void buscaPostTest(){
		
		Post post = postDAO.buscaPorId(1);		
		System.out.println("Post Titulo: " + post.getTitulo());
		System.out.println("Comentarios : " + post.getComentarios());
		
		System.out.println("Comentarios : " + post.getComentarios());
		
		Assert.assertNotNull("El metodo para buscar post regresa datos vacios",post);
		
	}
	
	@Test
	public void guardarPostTest(){
		
		Post post = new Post();
		//Ya no es necesario puesto que ya espesificamos la secuensa incremental
//		post.setId(10);
		post.setTitulo("Test");
		post.setContenido("TestContenido");
		
		Date fechaAct = new Date();
		post.setFechaCreacion(fechaAct);

		
		Integer guardaBD = postDAO.guardar(post);
		Post postID = postDAO.buscaPorId(guardaBD);	
		
		Assert.assertNotNull("El metodo para buscar post regresa datos vacios",postID);
		
		Assert.assertNotNull(postID.getId());
		Assert.assertEquals(post.getId(), postID.getId());
		
		Assert.assertNotNull(postID.getTitulo());
		Assert.assertEquals(post.getTitulo(), postID.getTitulo());
		
		
		Assert.assertNotNull(postID.getFechaCreacion());
		SimpleDateFormat formato = new SimpleDateFormat("yyy-MM-dd");
//		Assert.assertEquals(formato.format(post.getFechaCreacion()),formato.format(postID.getTitulo()));
	}
	
	@Test
	public void buscaPorTituloTest(){

		 List<Post> listaEncontrada = postDAO.buscaPorTitulo("post");
		 
		 System.out.println("POST TUTULO ENCONTRADOS : "+listaEncontrada.size());
		 
		 for (Post post : listaEncontrada) {
			 System.out.println("POST TUTULO ENCONTRADOS : "+post.getTitulo());
		}
		 
		 //NOT EMPTY REVISA QUE LA COLECCION NO SEA NULA Y QUE NO VENGA VACIA
		 //ES DECIR QUE TENGA UNO O MAS ELEMENTOS
		 notEmpty(listaEncontrada);
		 
	}


}
