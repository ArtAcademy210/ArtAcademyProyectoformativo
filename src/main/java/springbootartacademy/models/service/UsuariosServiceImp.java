package springbootartacademy.models.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import net.bytebuddy.utility.RandomString;
import springbootartacademy.models.dao.IUsuariosDao;
import springbootartacademy.models.entity.*;
import springbootartacademy.utils.Utilidad;
@Service
public class UsuariosServiceImp implements IUsuariosService {
	@Autowired
	private IUsuariosDao usudao;
	@Autowired
	private JavaMailSender mailSender ;

	public Usuarios findByCorreo(String correo) {
		return usudao.findByCorreo(correo);
	}
	public Usuarios findByNombreusuario(String nombreusuario) {
		return usudao.findByNombreusuario(nombreusuario);
	}
	public boolean verificacionenlace(String verification)
	{
		Usuarios verificacion = usudao.findByVerification(verification);
		if(verificacion == null){return false;}
		else 
		{
			usudao.actualizaestado(verificacion.getId());
			return true;
		}	
	}
	public void saveUsuarios(Usuarios usuarios) {
		String token = RandomString.make(45);
		usuarios.setVerification(token);
		usudao.findByNombreusuario(usuarios.getNombreusuario());
		usudao.findByCorreo(usuarios.getCorreo());
		String encodedPassword = Utilidad.passwordencode().encode(usuarios.getContraseña());
		usuarios.setContraseña(encodedPassword);
		usuarios.setEstado(false);
		usudao.save(usuarios);
	}
	public Usuarios getToken(String resetPasswordToken) {
		// TODO Auto-generated method stub
		return usudao.findByResetPasswordToken(resetPasswordToken);
	}
	public void sendVerificationEmail(Usuarios nuevousuario,String siteURL)throws UnsupportedEncodingException, MessagingException {
		String subject ="Por favor verifica tu registro";
		String verificion = siteURL+"/verificate?code="+nuevousuario.getVerification();
		String mailcontent = "<p>Señ@r Usuario "+nuevousuario.getNombreusuario()+" para poder acceder por completo a nuestra pagina web debe de verificarse presionando en el siguiente link </p>";
		mailcontent+=" <p>Art Academy Team</p>";
		mailcontent+="<a href=\""+verificion+"\">Verificacion de Cuenta</a>";
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(nuevousuario.getCorreo());
		helper.setSubject(subject);
		helper.setText(mailcontent, true);
		mailSender.send(message);
	}


}
