package com.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.CustomersDto;


@Controller
public class LoginController {
	
	
	
	
	@RequestMapping( value = "/mylogin", method = RequestMethod.GET)
	public String myLogin() {
		
		
		
		return "login-page";
		
	}
	
	
	
	@RequestMapping( value = "/process-login", method = RequestMethod.POST)
	public String processLoginPage() {
		
		
		return "redirect:/hello";
		
	}
	
	
	@RequestMapping( value = "/hello", method = RequestMethod.GET)
	public String helloPage(Model model,Principal principal,Authentication authentication) {
		
		String name = principal.getName();
		model.addAttribute("name", name);
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        model.addAttribute("role", authorities);
		
		return "hello-page";
		
	
	}
	
	
/*	@RequestMapping(value ="/fail_login", method = RequestMethod.POST)
	public String failLogin() {
		System.out.println("Login is fail");
		
		return "redirect:/mylogin?error";
	}
	
	*/
	
	
	
	@RequestMapping(value ="/coders", method = RequestMethod.GET)
	public String coderPage(@ModelAttribute("customerDto")CustomersDto customersDto) {
		
		
		return "coder-page";
	}
	
	
	
	@GetMapping("/unauthorized")
	public String unauthorized() {
		return "unauthorized-user";
	}
	

}
