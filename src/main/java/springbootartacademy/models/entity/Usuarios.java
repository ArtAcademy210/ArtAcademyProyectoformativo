package springbootartacademy.models.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Usuarios {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
	@Column(length=50, nullable=false, unique=true)
private String nombreusuario;
	
private String contraseña;
	@Column(length=50, nullable=false, unique=true)
	
private String correo;
<<<<<<< HEAD
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="usuarios_id")
private Set<Roles> roles ;
=======
	@ManyToMany
	
private Set<Roles> roles;
>>>>>>> 97e5f6495b0007197334c956edd606b74dc84fd5
@Column(name="reset_password_token", length=45, nullable=true)

private String resetPasswordToken;
<<<<<<< HEAD
@Column(name="verification_token", length=45, updatable=false, nullable=false)
private String verification;
private boolean estado;

=======

private boolean estado; 
>>>>>>> 97e5f6495b0007197334c956edd606b74dc84fd5

	public boolean isEstado() {
	return estado;
}
public void setEstado(boolean estado) {
	this.estado = estado;
}
<<<<<<< HEAD
	public String getVerification() {
	return verification;
	}
	public void setVerification(String verification) {
	this.verification = verification;
	}
=======
>>>>>>> 97e5f6495b0007197334c956edd606b74dc84fd5
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreusuario() {
		return nombreusuario;
	}
	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Set<Roles> getRoles() {
		return roles;
	}
	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	public String getResetPasswordToken() {
		return resetPasswordToken;
	}
	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}
}
