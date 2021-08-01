package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dao.ICustomerDao;
import com.dto.CustomersDto;

@Controller
public class RegistrationController {
	
	@Autowired
	private ICustomerDao icDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@ModelAttribute("customerDto")
	public CustomersDto  getCustomer() {
		
		CustomersDto customersDto = new CustomersDto();
		
		return customersDto;
		
	}
	
	
	
	@GetMapping("/registration")
	public String registrationPage(@ModelAttribute("customerDto")CustomersDto customersDto) {
		
		
		return "registration-page";
	}
	
	
	
	@PostMapping("/registrationProcess")
	public String processPage(@ModelAttribute("customerDto")CustomersDto customersDto) {
		
		String encode = passwordEncoder.encode(customersDto.getPassword());
		customersDto.setPassword(encode);
		customersDto.setEnabled(true);
		icDao.insertIntoCustomers(customersDto);
		
		
		return "hello-page";
	}

}
