package com.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomersDto {
	
	private int id;
	private String name;
	private String password;
	private String username;
	private String gender;
	private String city;
	private boolean chack;
	private boolean enabled;
	private boolean account_non_locked;
	private int failed_attempt;
	private Date lock_time;
	private List<SimpleGrantedAuthority>roles;
	private  int id_user;
	private int id_authorities;
	
	public int getId() {
		return id;
	}
	
	
	
	public boolean isAccount_non_locked() {
		return account_non_locked;
	}



	public void setAccount_non_locked(boolean account_non_locked) {
		this.account_non_locked = account_non_locked;
	}



	public int getFailed_attempt() {
		return failed_attempt;
	}



	public void setFailed_attempt(int failed_attempt) {
		this.failed_attempt = failed_attempt;
	}



	public Date getLock_time() {
		return lock_time;
	}



	public void setLock_time(Date lock_time) {
		this.lock_time = lock_time;
	}



	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public boolean isChack() {
		return chack;
	}
	public void setChack(boolean chack) {
		this.chack = chack;
	}



	


	

	public List<SimpleGrantedAuthority> getRoles() {
		return roles;
	}



	public void setRoles(List<SimpleGrantedAuthority> roles) {
		this.roles = roles;
	}



	public int getId_user() {
		return id_user;
	}



	public void setId_user(int id_user) {
		this.id_user = id_user;
	}



	public int getId_authorities() {
		return id_authorities;
	}



	public void setId_authorities(int id_authorities) {
		this.id_authorities = id_authorities;
	}










	


	
}
