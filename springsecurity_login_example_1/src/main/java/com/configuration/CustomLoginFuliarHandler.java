package com.configuration;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.dao.ICustomerDao;
import com.dto.CustomersDto;


@Component
public class CustomLoginFuliarHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	ICustomerDao customerDao;
	
	public static final int MAX_FAILD_ATTEMPTS = 3;

	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String username = request.getParameter("customusername");
		
		//metoda koja uspisuje podatke iz tabele customers
		  List<CustomersDto> getusername = customerDao.getusername(username);
		
		
		
		  //ukoliko objekat nije prazan
			if (!getusername.isEmpty() ) {
	             //ukoliko objekat getuje account_non+losked
				if (getusername.get(0).isAccount_non_locked()) {
					//ukoliko je broj greska pri logovanju manji od 3 izvravaj update
					if (getusername.get(0).getFailed_attempt() < MAX_FAILD_ATTEMPTS - 1) {
						customerDao.updatefailed_attempt(getusername.get(0));
					} else {
						//zakljucaj nalog kada korisnik pogresi 3 puta
						customerDao.lockUser(getusername.get(0));
						exception =  new LockedException("Vas nalog ce biti zakljucan nakon 3 pokusaja logovanja na 15min");

					}
					//ukoliko nije otkljucan, ukoliko je false
				}else if (!getusername.get(0).isAccount_non_locked()) {
					if (customerDao.unlock(getusername.get(0))) {
						exception = new LockedException("Vas nalog je otkljucan");

					}
				}
					
				}

			
	      super.setDefaultFailureUrl("/mylogin?error");
		  super.onAuthenticationFailure(request, response, exception);
		
		
	}
	
	
	
	
	
	
	
	
	

}
