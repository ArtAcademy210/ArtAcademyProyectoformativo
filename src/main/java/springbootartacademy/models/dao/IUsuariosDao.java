package springbootartacademy.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import springbootartacademy.models.entity.Usuarios;

public interface IUsuariosDao extends JpaRepository<Usuarios, Long> {
public Usuarios findByCorreo(String correo);
public Usuarios findByResetPasswordToken(String resetPasswordToken);

@Query ("select usuarios from Usuarios usuarios where usuarios.nombreusuario =:NombreUsuario")

public Usuarios getUsuarios(@Param("NombreUsuario") String NombreUsuario );

}


