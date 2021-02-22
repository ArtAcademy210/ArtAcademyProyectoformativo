package springbootartacademy.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootartacademy.models.entity.Usuarios;

public interface IUsuariosDao extends JpaRepository<Usuarios, Long> {
public Usuarios findByCorreo(String correo);
public Usuarios findByResetPasswordToken(String resetPasswordToken);
}
