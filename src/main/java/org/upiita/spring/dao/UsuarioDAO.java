package org.upiita.spring.dao;

import org.upiita.spring.entidades.Usuario;

public interface UsuarioDAO {

	public abstract Usuario buscaPorId(Integer id);

	public abstract Integer guardar(Usuario usuario);

	public abstract Usuario login(String usr, String pss);

}