package com.ibm.Repository;

import org.springframework.data.repository.CrudRepository;

import com.ibm.entity.Customer;



public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
