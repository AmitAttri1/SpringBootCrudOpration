package com.ibm.Service;

import java.util.List;


import com.ibm.dto.CustomerDto;
import com.ibm.exception.SBIException;

public interface CustomerService {
	public CustomerDto getCustomer(Integer customerId) throws SBIException;
	public List<CustomerDto> getAllCustomer() throws SBIException;
	public Integer addCustomer(CustomerDto customer)throws SBIException;

}
