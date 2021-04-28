package com.muhammedpiral.whatsapp.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.muhammedpiral.whatsapp.filter.JwtAuthenticationEntryPoint;
import com.muhammedpiral.whatsapp.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Resource(name = "userService")
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Bean
	public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationFilter();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		
//		sessionManagement()
//		  .sessionCreationPolicy(SessionCreationPolicy.NEVER)
//		  .and().
		
		http .csrf().disable().authorizeRequests().antMatchers("/login").permitAll()
		.antMatchers("/user/delete/*").hasAuthority("DELETE")
		        .antMatchers("/user/update").hasAnyAuthority("POST","PUT","DELETE")
		        .antMatchers("/user/save").hasAnyAuthority("POST","PUT","DELETE")
	            .antMatchers("/roles/**").hasAuthority("POST")
				.anyRequest().authenticated()
				.and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
				.and().httpBasic();

		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
