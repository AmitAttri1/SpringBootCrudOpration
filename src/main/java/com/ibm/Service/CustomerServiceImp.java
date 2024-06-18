package com.ibm.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.Repository.CustomerRepository;
import com.ibm.dto.CustomerDto;
import com.ibm.entity.Customer;
import com.ibm.exception.SBIException;


@Service(value="customerService")
@Transactional
public class CustomerServiceImp implements CustomerService{
	
     @Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerDto getCustomer(Integer customerId) throws SBIException {
		
		Optional<Customer> optional = customerRepository.findById(customerId);
		
		Customer customer = optional.orElseThrow(()-> new SBIException("Service.CUSTOMER_NOT_FOUND"));
		
		CustomerDto customerDto=new CustomerDto();
		customerDto.setCustomerId(customer.getCustomerId());
		customerDto.setName(customer.getName());
		customerDto.setDateOfBirth(customer.getDateOfBirth());
		customerDto.setEmailId(customer.getEmailId());
		return customerDto;
	}

	@Override
	public List<CustomerDto> getAllCustomer() throws SBIException {
		Iterable<Customer> customers = customerRepository.findAll();
	List<CustomerDto> customers2= new	ArrayList<>();
	customers.forEach(customer->{
		CustomerDto cust=new CustomerDto();
		cust.setCustomerId(customer.getCustomerId());
		cust.setDateOfBirth(customer.getDateOfBirth());
		cust.setEmailId(customer.getEmailId());
		cust.setName(customer.getName());
		customers2.add(cust);
	});
		if(customers2.isEmpty())
		{ throw new SBIException("Service.CUSTOMERS_NOT_FOUND");
			
		}
		
		return customers2;
	}

	@Override
	public Integer addCustomer(CustomerDto customer) throws SBIException {
		Customer customerEntity=new Customer();
		customerEntity.setCustomerId(customer.getCustomerId());
		customerEntity.setDateOfBirth(customer.getDateOfBirth());
		customerEntity.setEmailId(customer.getEmailId());
		customerEntity.setName(customer.getName());
		Customer customerEntity2 = customerRepository.save(customerEntity);
		return customerEntity2.getCustomerId();
	}

}
