package springbootartacademy.controllers;



import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import net.bytebuddy.utility.RandomString;
import springbootartacademy.models.entity.Usuarios;
import springbootartacademy.models.service.IResetPasswordService;
import springbootartacademy.utils.UsersNotFoundException;
import springbootartacademy.utils.Utilidad;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

@Controller
public class AccountController {

@Autowired
private IResetPasswordService passser;
@GetMapping("/login")
public String login() {
	return "frontend/cuenta/login";
}
@GetMapping("/recuperarpassword")
public String resetContraseña(){
	return "frontend/recuperarcontraseña/recuperarcontraseña";
}
@PostMapping("/resetpassword")
public String resetPassword(HttpServletRequest request, Model model) {
	String correo = request.getParameter("correo");
	String token = RandomString.make(45);
	try {
		
		
		passser.updateContraseña(token, correo);
		String recuperarContraseñaUrl= Utilidad.getSitioUrl(request) + "/formulariocontraseña?token="+token;
		passser.sendTokenCorreo(correo, recuperarContraseñaUrl);
		model.addAttribute("info", "Se envío un enlace a tu correo por favor revisa");
		
		} catch (UsersNotFoundException e) {
			model.addAttribute("error", e.getMessage());
		}catch (UnsupportedEncodingException | MessagingException  e) {
		model.addAttribute("error", "error al enviar el token");
	} 
	return "frontend/recuperarcontraseña/recuperarcontraseña";
}

	@GetMapping("/formulariocontraseña")
	public String formContraseña(@Param(value="token") String token, 
			Model model) {
		Usuarios usuarios = passser.get(token);
		if(usuarios==null) {
			model.addAttribute("error", "Error no se puede acceder a este formulario");
			return "redirect:/recuperarpassword";
		}
		model.addAttribute("token", token);
		return "frontend/recuperarcontraseña/formularioContraseña";
	}
	@PostMapping("/cambiarcontraseña")
	public String cambiarContraseña(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");
		Usuarios usuarios = passser.get(token);
		if(usuarios==null) {
			model.addAttribute("error", "Error no se puede acceder a este formulario");
		}else {
			passser.updatenuevaContraseña(usuarios, password);
			model.addAttribute("info", "Se cambio su contraseña de forma correcta");
		}
		
		return "frontend/cuenta/login";
	}
}
