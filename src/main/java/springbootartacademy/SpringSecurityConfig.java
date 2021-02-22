package springbootartacademy;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
private static final String[] rutaconfiguracion = {
	"/recuperarcontraseña/**",
	"/login/**"
};
protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests()
	.antMatchers(rutaconfiguracion).permitAll();
}
}
