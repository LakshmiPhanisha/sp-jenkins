package com.mss.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.mss.demo.entity.Customer;
import com.mss.demo.repo.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	public Mono<Customer> createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Flux<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Mono<Customer> getCustomerById(String id) {
		return customerRepository.findById(id);
	}

	public Mono<Customer> updateCustomer(String id, Customer customer) {
		return customerRepository.findById(id).flatMap(existingCustomer -> {
			existingCustomer.setName(customer.getName());
			existingCustomer.setEmail(customer.getEmail());
			return customerRepository.save(existingCustomer);
		});
	}

	public Mono<Void> deleteCustomer(String id) {
		return customerRepository.deleteById(id);
	}

}
