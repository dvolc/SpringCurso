package org.upiita.spring.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;



public class PostForm {
	
	private Integer id;
	
	
	private Date fechaCreacion;

	//Valida que una cadena o una coleccion de java tenga al menos un elemento
	@NotEmpty
	private String titulo;

	//Size es para cadenas o colecciones
	@Size(min=5)
	private String contenido;


	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getContenido() {
		return contenido;
	}


	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

}
