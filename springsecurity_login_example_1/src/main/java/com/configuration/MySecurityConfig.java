package com.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	
	@Autowired
	MyCustomFormAuthenticationProvider provider;
	
	@Autowired
	CustomLoginFuliarHandler fuliar;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(provider);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/coders").hasAuthority("CODER").antMatchers("/hello").permitAll()
				.antMatchers("/registration").permitAll()

				.and().formLogin().loginPage("/mylogin").permitAll().usernameParameter("customusername")
				.passwordParameter("customPassword").loginProcessingUrl("/process-login")
				.successForwardUrl("/process-login").failureHandler(fuliar)

				.and().logout().logoutSuccessHandler(new LogoutSuccessHandler() {

					@Override
					public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth)
							throws IOException, ServletException {

						res.sendRedirect("/springsecurity_login_example_1/home");

					}
				})

				.and().exceptionHandling().accessDeniedPage("/unauthorized");

	}
	
	

}
