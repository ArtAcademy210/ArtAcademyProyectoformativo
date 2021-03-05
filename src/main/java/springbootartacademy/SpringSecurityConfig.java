package springbootartacademy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springbootartacademy.utils.UsuarioDetailsImp;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
 @Autowired
 private UsuarioDetailsImp usuarioDetailsImp;

 @Bean
 public BCryptPasswordEncoder passwordEncoder() {
	 return new BCryptPasswordEncoder();
 }
 
 

 @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/frontend/css/**", "/frontend/js/**", "/frontend/webfonts/**", "/images/**", "/recuperarpassword/**", "/resetpassword/**", "/formulariocontraseña/**", "/cambiarcontraseña/**","/error403/**").permitAll()
		.antMatchers("/inicio").hasAuthority("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login") .permitAll()
		.and()
		.logout().permitAll();
	}
 @Autowired
	protected void configurerGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDetailsImp)
		.passwordEncoder(passwordEncoder());
		
	}
}
