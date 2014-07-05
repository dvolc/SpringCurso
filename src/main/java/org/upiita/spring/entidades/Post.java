package org.upiita.spring.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name="posts")
public class Post {
	//post_id_seq
	@Id
	@SequenceGenerator(name="idPostSecuencia", sequenceName="post_id_seq",allocationSize=1)
	@GeneratedValue(generator="idPostSecuencia",strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="titulo")
	private String titulo;

	@Column(name="contenido")
	private String contenido;
	
	
	// a UN POST LE CORRESPONDEN MUCHOS COMENTARIOS
	@OneToMany(mappedBy="post",fetch=FetchType.EAGER)
	private List<Comentario> comentarios;

	
	@ManyToOne()
	@JoinColumn(name="usuario_id")
	private Usuario usuarioM; 
	
	
	@ManyToMany(mappedBy="listPost")
	private List<Categoria> listCategorias;
	
	
	
	
	public List<Categoria> getListCategorias() {
		return listCategorias;
	}

	public void setListCategorias(List<Categoria> listCategorias) {
		this.listCategorias = listCategorias;
	}

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

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Usuario getUsuarioM() {
		return usuarioM;
	}

	public void setUsuarioM(Usuario usuarioM) {
		this.usuarioM = usuarioM;
	}




	
}
