package springbootartacademy.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootartacademy.models.dao.IUsuariosDao;
import springbootartacademy.models.entity.Usuarios;
@Service
public class UsuariosServiceImp implements IUsuariosService {
	@Autowired
	private IUsuariosDao usudao;

	public Usuarios findByCorreo(String correo) {
		return usudao.findByCorreo(correo);
	}

	public void saveUsuarios(Usuarios usuarios) {
		usudao.save(usuarios);
		
	}

	public Usuarios getToken(String resetPasswordToken) {
		// TODO Auto-generated method stub
		return usudao.findByResetPasswordToken(resetPasswordToken);
	}

}
