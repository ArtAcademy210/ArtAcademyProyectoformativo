package springbootartacademy.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import springbootartacademy.models.dao.IUsuariosDao;
import springbootartacademy.models.entity.Usuarios;

public class UsuarioDetailsImp implements UserDetailsService {

@Autowired 
private IUsuariosDao DAO;  
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuarios usuarios=DAO.getUsuarios(username);
		if(usuarios == null) {
			throw new UsernameNotFoundException ("El usuario no existe");
		}
		return new MyUsuarioDetails(usuarios);
	}

}
