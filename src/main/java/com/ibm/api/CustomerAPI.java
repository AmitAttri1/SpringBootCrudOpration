package com.ibm.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.Service.CustomerService;
import com.ibm.dto.CustomerDto;
import com.ibm.exception.SBIException;

@RestController
@RequestMapping(value = "/SBI")
public class CustomerAPI {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private Environment environment;

	
//http://localhost:8085/SBI/customers/2
	
	
	@GetMapping(value = "/customers/{customerId}")
 public ResponseEntity<CustomerDto> getCustomer(@PathVariable Integer customerId) throws SBIException
{
		CustomerDto customer = customerService.getCustomer(customerId);
		System.out.println("customerid::>"+customerId);
	return new ResponseEntity<>(customer, HttpStatus.OK);
		
}
	@GetMapping(value = "/customers")
	public  ResponseEntity<List<CustomerDto>> getAllCustomer() throws SBIException
	{
		List<CustomerDto> customerList = customerService.getAllCustomer();
		return new ResponseEntity<>(customerList, HttpStatus.OK);	
	}
	
	@PostMapping(value = "/customers")
	public ResponseEntity<String> addCustomer(@RequestBody CustomerDto customer) throws SBIException
	{
		Integer customerId = customerService.addCustomer(customer);
		String successMessage = environment.getProperty("API.INSERT_SUCCESS") + customerId;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
		
	}
	
}
