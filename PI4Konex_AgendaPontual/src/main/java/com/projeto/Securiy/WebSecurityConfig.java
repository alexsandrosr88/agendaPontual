package com.projeto.Securiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;/*
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;*/

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig /*extends WebSecurityConfigurerAdapter*/{
/*
	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()//home
		.antMatchers(HttpMethod.GET, "/medico/busca").permitAll()//resultado busca
		.antMatchers(HttpMethod.GET, "/medico/resumo").permitAll()//resumo
		.antMatchers(HttpMethod.GET, "/sobre-nos").permitAll()//sobre nos
		.antMatchers(HttpMethod.GET, "/planos").permitAll()//planos
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//aqui 
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());//gera uma senha criptografada
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		//passa sem autenticação
		web.ignoring().antMatchers("/static/**", "/templates/**");
	}*/
}