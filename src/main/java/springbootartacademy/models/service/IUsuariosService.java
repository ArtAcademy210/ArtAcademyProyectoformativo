package springbootartacademy.models.service;

import springbootartacademy.models.entity.Usuarios;

public interface IUsuariosService {
public Usuarios findByCorreo(String correo);
public void saveUsuarios(Usuarios usuarios);
public Usuarios getToken(String resetPasswordToken);
}
