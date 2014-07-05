package org.upiita.spring.dao;

import java.util.List;

import org.upiita.spring.entidades.Post;

public interface PostDAO {

	public abstract Post buscaPorId(Integer id);

	public abstract Integer guardar(Post post);

	public abstract List<Post> buscaPorTitulo(String valor);

	public abstract List<Post> buscaDiferentesTitulo(String valor);

}