package com.mss.demo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;


@Document(collection = "customers")
@Data
public class Customer {
    @Id
    private String id;
    private String name;
    private String email;
}
