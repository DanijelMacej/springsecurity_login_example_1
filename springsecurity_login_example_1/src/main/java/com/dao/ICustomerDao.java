package com.dao;

import java.util.List;

import com.dto.CustomersDto;

public interface ICustomerDao {
	
	
	 List<CustomersDto> findUserByUserEmail(String email);
	 
	 int getcityId(String country);
	 
	 int getgenderId(String gender);
	 
	 int getuserId(String userName);
	 
	 void insertIntoCustomers (CustomersDto customersDto);
	 
	 void updatefailed_attempt(CustomersDto customersDto);
	 
	 int insertFailedAttempt(int faildAttempt);

	void lockUser(CustomersDto customersDto);
	
	List<CustomersDto>getusername (String email) ;
	
	 boolean unlock(CustomersDto customersDto);
	


}
