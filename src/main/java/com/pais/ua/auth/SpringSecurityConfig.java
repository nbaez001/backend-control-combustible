package com.pais.ua.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Agregando reglas por el lado de spring
@EnableGlobalMethodSecurity(securedEnabled=true)//Habilitar uso de anotaciones de seguridad - roles
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService usuarioService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
	}

	@Bean("authenticationManager")
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.csrf().disable()//Deshabilitar la proteccion csrf = cross-site request forgery o falsificacion de peticion en sitios cruzados
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//Deshabilitar el manejo de sesiones en la autenticacion por lado de spring security porque se va a trebajar con tokens
		//STATELESS = sin estado
		//http.headers().frameOptions().sameOrigin();
		http.headers().frameOptions().sameOrigin().contentSecurityPolicy("frame-ancestors 'self'");
	}
	
	//Acceso a swagger-ui.html
	@Override
    public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/v2/api-docs",
				"/configuration/ui",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html",
				"/webjars/**",
				"/",
				"/error",
				"/images/**",
				"/js/**",
				"/css/**");
    }

}
