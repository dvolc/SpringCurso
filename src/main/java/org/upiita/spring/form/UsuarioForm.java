package org.upiita.spring.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;



public class UsuarioForm {
	
	
	private Integer id;

	@NotEmpty
	private String password;

	@NotEmpty
	private String nombre;

	@Size(min=3)
	private String email;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
