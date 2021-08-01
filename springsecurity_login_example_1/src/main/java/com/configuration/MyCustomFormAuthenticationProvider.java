package com.configuration;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dto.CustomersDto;


//Custom AuthenticationProvider
@Component
public class MyCustomFormAuthenticationProvider implements AuthenticationProvider  {

	@Autowired
	private com.dao.ICustomerDao customerDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	public static final int LOCK_TIME_DURACTION = 15 * 60 * 1000;

	
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	
		String username = authentication.getName();   //authentication - authentcationObject
		String password = authentication.getCredentials().toString();
		
		//metoda koja ispisuje podatke iz tabele customers i authorities
		List<CustomersDto> customers = customerDao.findUserByUserEmail(username);
			
		
		if (customers.size() > 0) {
			// daj objekat iz baze
			CustomersDto customer = customers.get(0);
			// uporedi password koji je korisnik uneo i password iz baze
			boolean matches = passwordEncoder.matches(password, customer.getPassword());

			System.out.println(customer.getPassword());
			// ukoliko se slazu daj mi roles iz baze
			if (matches) {

				Collection<? extends GrantedAuthority> roles = customers.get(0).getRoles();

				System.out.println(roles);

				// sve podatke smesti u UsernamePasswordAuthenticationToken objekat
				return new UsernamePasswordAuthenticationToken(username, password, roles);
			

			} else {
				throw new BadCredentialsException("invalide username/password");
			}

		} else {
			throw new BadCredentialsException("User does not exist..");

		}
		
	
}
	

	public boolean supports(Class<?> authentication) {
		// check authentication type
		// AuthenticationMananger ce znati koji AuthenticationProvider treba da pozove na osnvu vrste authencationObjekta
		//Ukoliko se aktivirao UsernamePasswordAuthenticationFilter on ce napraviti UsernamePasswordAuthenticationTokenObject
		//koji ce proslediti taj objekat do AuthenticationManagera i on ce traziti AuthenticationProvider  cija supports metoda vraca UsernamePasswordAuthenticationToken klasu
		//
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	

}
