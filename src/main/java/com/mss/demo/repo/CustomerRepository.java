package com.mss.demo.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.mss.demo.entity.Customer;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String>{

}
