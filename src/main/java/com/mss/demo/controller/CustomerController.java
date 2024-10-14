package com.mss.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.mss.demo.entity.Customer;
import com.mss.demo.service.CustomerService;

@RestController
@RequestMapping("/webflux")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	 @GetMapping("/all")
	    public Flux<Customer> getAllCustomers() {
	        return customerService.getAllCustomers();
	    }

	    @GetMapping("/{id}")
	    public Mono<ResponseEntity<Customer>> getCustomerById(@PathVariable String id) {
	        return customerService.getCustomerById(id)
	                .map(customer -> ResponseEntity.ok(customer))
	                .defaultIfEmpty(ResponseEntity.notFound().build());
	    }

	    @PostMapping("/addData")
	    public Mono<Customer> createCustomer(@RequestBody Customer customer) {
	        return customerService.createCustomer(customer);
	    }

	    @PutMapping("/{id}")
	    public Mono<ResponseEntity<Customer>> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
	        return customerService.updateCustomer(id, customer)
	                .map(updatedCustomer -> ResponseEntity.ok(updatedCustomer))
	                .defaultIfEmpty(ResponseEntity.notFound().build());
	    }

	    @DeleteMapping("/{id}")
	    public Mono<ResponseEntity<Object>> deleteCustomer(@PathVariable String id) {
	        return customerService.deleteCustomer(id)
	                .then(Mono.just(ResponseEntity.noContent().build()))
	                .onErrorResume(e -> Mono.just(ResponseEntity.notFound().build()));
	    }

}
