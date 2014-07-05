package org.upiita.spring.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = "datos_usuarios")
public class DatosUsuario {

	@Id
	@SequenceGenerator(name="datosUsuarioIdSecuencia", sequenceName="datos_usuarios_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="datosUsuarioIdSecuencia")
	@Column(name = "id")
	private Integer id;

	@Column(name = "biografia")
	private String biografia;

	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	//EN LA ENTIDAD QUE NO ES DUEÑA VA LA PRODIEDAD mappedBy
	//se llena con el nombre de la propiedad del la otra clase que estamos mapenado
	@OneToOne(mappedBy="datosUsuarios")
	private Usuario usuario;
	
	// override del metodo String para que esta clase se imprima bonito :)
	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append("{");
		builder.append("id:").append(this.id).append(",");
		builder.append("biografia:").append(this.biografia).append(",");
		builder.append("fechaRegistro:").append(this.fechaRegistro);
		builder.append("}");

		return builder.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
