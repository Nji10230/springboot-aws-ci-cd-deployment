package com.example.dataJpa_relations.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String zipcode;


    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String City;

    @OneToOne(mappedBy = "address")
    @JsonBackReference("order-address")
    private Order order;


    public Address(Long id, String zipcode, String street, String city, Order order) {
        this.id = id;
        this.zipcode = zipcode;
        this.street = street;
        City = city;
        this.order = order;
    }

    public Address() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
