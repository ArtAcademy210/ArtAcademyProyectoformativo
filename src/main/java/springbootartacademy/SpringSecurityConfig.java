package springbootartacademy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springbootartacademy.utils.UsuarioDetailsImp;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
 @Bean
	public UserDetailsService userDetailsService() {
		return new UsuarioDetailsImp();
	}
 @Bean
 	public BCryptPasswordEncoder passwordEncoder() {
 		return new BCryptPasswordEncoder();
 	}
 	
 @Bean
 public DaoAuthenticationProvider authenticationProvider() {
	 DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
	 daoAuthenticationProvider.setUserDetailsService(userDetailsService());
	 daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	  return daoAuthenticationProvider;
 }
 
 @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
	 auth.authenticationProvider(authenticationProvider());
	} 
 
 @Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.antMatchers("/frontend/css/**","/frontend/js/**","/frontend/webfonts/**","/recuperarpassword/**","/resetpassword/**","/formulariocontraseña/**","/cambiarcontraseña/**").permitAll()
		.anyRequest().authenticated().and()
		.formLogin().permitAll().and().logout().permitAll();
	}
 
}
