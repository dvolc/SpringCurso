package org.upiita.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.upiita.spring.entidades.Post;

@Component(value="postDAO")
@Transactional
public class HibernatePostDAO implements PostDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see org.upiita.spring.dao.PostDAO#buscaPorId(java.lang.Integer)
	 */
	@Override
	public Post buscaPorId(Integer id){
		Post elPost = null;
		
		//TRANSACCION PROGRAMATICA		
		//Session sesion = sessionFactory.openSession();		
		//sesion.beginTransaction();
		
		Session sesion = sessionFactory.getCurrentSession();
		
		//UNA VEZ INICIADA LA TRANSACCION PODEMOS HACER CONSULTAS
		//O MODIFICAR LA BASE
		//SI HIBERNATE ENCUENTRA EL RENGLON, EL OBJETO SE RELLENA AUTOMATICAENTE
		//SI NO LO ENCUENTRA, HIBERNATE REGRESA NULL
		elPost = (Post) sesion.get(Post.class,id);
		
		//Le indica a Hiernate que queremos sus comentarios asociados
		//Esto sin usar el fetch Eager
		//Hibernate.initialize(elPost.getComentarios());
		
		
		//TERMINADO TODO LO QUE NECESITAMOS HACER EN LA BASE
		//CERRAMOS LA SESION DE HIBERNATE
		//sesion.close();
				
		//SIMULAMOS LA CONSULTA  AL ABASE
		//NOS REGRESO ESTE OBJETO
		/*
		elPost = new Post();
		elPost.setTitulo("titulo prueba");
		elPost.setContenido("contenido prueba");
		*/		
		
		return elPost;
	}
	
	/* (non-Javadoc)
	 * @see org.upiita.spring.dao.PostDAO#guardar(org.upiita.spring.entidades.Post)
	 */
	@Override
	public Integer guardar(Post post){
		
		//Session sesion = sessionFactory.openSession();		
		//sesion.beginTransaction();
		
		Session sesion = sessionFactory.getCurrentSession();
		
		//UNA VEZ INICIADA LA TRANSACCION PODEMOS HACER CONSULTAS
		//O MODIFICAR LA BASE
		//SI HIBERNATE ENCUENTRA EL RENGLON, EL OBJETO SE RELLENA AUTOMATICAENTE
		//SI NO LO ENCUENTRA, HIBERNATE REGRESA NULL
		//ESTE METODO NO REGRESA NADA
		// PERO!!!
		// ACUTALIZA EL OBJETO QUE LE PASAN 
		// EN PARTICULAR EL ID
		sesion.saveOrUpdate(post);
		
		//TERMINADO TODO LO QUE NECESITAMOS HACER EN LA BASE
		//CERRAMOS LA SESION DE HIBERNATE
		//SI CAMBIAN DATOS HACER EL COMMIT!!!
		//SI NO SE VEN LOS CAMBIOS
		//sesion.getTransaction().commit();
		//sesion.close();		
		
		return post.getId();
	}
	
	@Override
	public List<Post> buscaPorTitulo(String valor){
		
		Session session = sessionFactory.getCurrentSession();
		//7A partir de la session creamos el criterio usando el .class que representa la Tabla
		Criteria criterio= session.createCriteria(Post.class);
		
		// Agregamos criterios de busqueda
		//PRIMER ARGUMENTO DE LIKE ES LA RESTRICCION DE LA PROPIEDAD DE LA ENTIDAD A BUSCAR
		criterio.add(Restrictions.like("titulo", "%"+valor+"%"));
		
		//LE DECIMOS A HIBERNATE QUE BUSQUE Y OBTENGA UNA LISTA DE RESULTADOS
		List<Post> listPost = criterio.list();
		
		System.out.println("Lista de Resultados : "+ listPost.size());
		
		return listPost;
		
	}
	
	
	@Override
	public List<Post> buscaDiferentesTitulo(String valor){
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criterio= session.createCriteria(Post.class);
		criterio.add(Restrictions.not(Restrictions.like("titulo", "%"+valor+"%")));
		List<Post> listPost = criterio.list();
		
		System.out.println("Lista de Resultados : "+ listPost.size());
		
		return listPost;
		
	}

}
