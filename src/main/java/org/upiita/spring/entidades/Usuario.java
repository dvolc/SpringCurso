package org.upiita.spring.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name="usuarios")
public class Usuario {
	//usuarios_id_seq
	@Id
	@SequenceGenerator(name="idUsrSecuencia", sequenceName="usuarios_id_seq",allocationSize=1)
	@GeneratedValue(generator="idUsrSecuencia",strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;

	@Column(name="password")
	private String password;

	@Column(name="nombre")
	private String nombre;

	@Column(name="email")
	private String email;
	
	@OneToOne
	//En la entidad dueña siempre va el Anotation JOin Column
	//La entidad dueña es la que tiene la llave foranea
	@JoinColumn(name="datos_autor_id")
	private DatosUsuario datosUsuarios;
	
	
	@Fetch(value = FetchMode.SELECT)
	@OneToMany(mappedBy="usuarioM",fetch=FetchType.EAGER)
	private List<Post> post;

	
	@Fetch(value = FetchMode.SELECT)
	@ManyToMany(mappedBy="listUsuarios",fetch=FetchType.EAGER)
	private List<Departamento> departammento;
	
	
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
	
	

	public DatosUsuario getDatosUsuarios() {
		return datosUsuarios;
	}

	public void setDatosUsuarios(DatosUsuario datosUsuarios) {
		this.datosUsuarios = datosUsuarios;
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

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public List<Departamento> getDepartammento() {
		return departammento;
	}

	public void setDepartammento(List<Departamento> departammento) {
		this.departammento = departammento;
	}




}
