package springbootartacademy.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import springbootartacademy.models.entity.Usuarios;

public interface IUsuariosDao extends JpaRepository<Usuarios, Long> {
public Usuarios findByCorreo(String correo);
public Usuarios findByNombreusuario(String nombreusuario);
public Usuarios findByResetPasswordToken(String resetPasswordToken);
@Transactional
@Modifying
@Query("update Usuarios u set u.estado=true, u.verification='' where u.id=?1")
public void actualizaestado(Long id);
@Query("Select u from Usuarios u where u.verification=?1")
public Usuarios findByVerification(String code);
}
