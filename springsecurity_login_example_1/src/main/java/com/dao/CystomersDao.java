package com.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dto.CustomersDto;

@Repository
public class CystomersDao implements ICustomerDao {
	
	//zakljucavanje na 15min
	private static final int LOCK_TIME_DURACTION = 15 * 60 * 1000;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	
	public List<CustomersDto> findUserByUserEmail(String username) {

		String queryAut = "SELECT customers.password, customers.enabled, customers.username , authorities.roles FROM customers, authorities WHERE customers.username = ? AND customers.id =authorities.id_user ";
		List<CustomersDto> listOfCustomers = jdbcTemplate.query(queryAut,
				new BeanPropertyRowMapper<CustomersDto>(CustomersDto.class), username);
		return listOfCustomers;

	}
	
	
	
	public int getcityId(String country) {
		String query = "SELECT id_city FROM city WHERE city_name = ?";
		Integer queryForObject1 = jdbcTemplate.queryForObject(query, new Object[] {country}, int.class);
		return queryForObject1;
	}


	public int getgenderId(String gender) {
		String query = "SELECT id_gender FROM gender WHERE type_of_sex = ?";
		Integer queryForObject2 = jdbcTemplate.queryForObject(query, new Object [] {gender},int.class);
		return queryForObject2;
	}
	
	
	public int getuserId(String username) {
		String query = "SELECT id FROM customers WHERE username = ?";
		Integer queryForObject = jdbcTemplate.queryForObject(query, new Object[] {username}, int.class);
		return queryForObject;
	}
	

	
	
	
	
       public void insertIntoCustomers (CustomersDto customersDto) {
		
    	   customersDto.setEnabled(false);
    	   customersDto.setAccount_non_locked(true);
    	   customersDto.setFailed_attempt(0);
    	   
		String queryCustomers = "INSERT INTO customers VALUES (null,?,?,?,?,?,?,?,?,?,?)";
		Object []argsCustomers = {customersDto.getName(),customersDto.getPassword(),customersDto.getUsername(),
				getgenderId(customersDto.getGender()), getcityId(customersDto.getCity()),customersDto.isChack(),customersDto.isEnabled(),
			 customersDto.getFailed_attempt(),  customersDto.isAccount_non_locked(),customersDto.getLock_time()};
		
		jdbcTemplate.update(queryCustomers,argsCustomers);
		
		
		String quryRoles ="INSERT INTO authorities VALUES (null,?,?)";
		Object []argsRoles = {getuserId(customersDto.getUsername()), "USER"};	
		
		jdbcTemplate.update(quryRoles,argsRoles);
		
		
	}
       
       public List<CustomersDto>getusername (String username) {
    	   String query = "select *from customers where username = ?";
    	  List<CustomersDto>list = jdbcTemplate.query(query,new BeanPropertyRowMapper<CustomersDto>(CustomersDto.class),username);
    	   return list;
    	   
       }
       
    
	
       public void updatefailed_attempt(CustomersDto customersDto) {
    	   String query = "UPDATE customers SET failed_attempt = ? where username = ?";
    	   Object [] args = {insertFailedAttempt(customersDto.getFailed_attempt()),customersDto.getUsername()};
    	   jdbcTemplate.update(query,args);
    	   
    	   
       }
       
       //pokusaju logovanja korisnika
       public int insertFailedAttempt(int faildAttempt) {
    	  int newFailedAttempt =  faildAttempt + 1;
    	  
    	  return newFailedAttempt;
    	 
    	   
       }

	@Override
	public void lockUser(CustomersDto customersDto) {
		customersDto.setAccount_non_locked(false);
		customersDto.setLock_time(new Date());
		
 	   String query = "UPDATE customers SET lock_time = ?, account_non_locked = ?  where username = ?";
 	   Object[]args = {customersDto.getLock_time(),customersDto.isAccount_non_locked(),customersDto.getUsername()};
 	   jdbcTemplate.update(query,args);

		
	}
	
	
	
	
	public boolean unlock(CustomersDto customersDto) {

		long lockTimeMilisec = customersDto.getLock_time().getTime();
		// daje trenutno vreme u milisekundama
		long curentTimeInMilisec = System.currentTimeMillis();
		// ukoliko je pocetak vremena zakljucavanja + 15min manje od trenutnog
		// vremena(tj ukoliko je proslo 15min) otkljucaj nalog , setuj vreme
		// zakljucabanja na pocetak kao i broj pokusaja na 0
		//da je stavljeno vece morao bi da se jos jednom ulogujem
		if (lockTimeMilisec + LOCK_TIME_DURACTION < curentTimeInMilisec) {

			customersDto.setAccount_non_locked(true);
			customersDto.setLock_time(null);
			customersDto.setFailed_attempt(0);
			String query = "UPDATE customers SET lock_time = ?, account_non_locked = ? , failed_attempt = ? where username = ?";
			Object[] args = { customersDto.getLock_time(), customersDto.isAccount_non_locked(),
					customersDto.getFailed_attempt(), customersDto.getUsername() };
			jdbcTemplate.update(query, args);
			return true;

		}
		return false;

	}
	
}